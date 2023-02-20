package com.company.milliyuniversity.service;

import com.company.milliyuniversity.domains.ImageMedia;
import com.company.milliyuniversity.domains.Speakers;
import com.company.milliyuniversity.dtos.SpeakerCreateDto;
import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.exceptions.GenericRuntimeException;
import com.company.milliyuniversity.repository.SpeakerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 08/02/23 Wednesday 10:41
 * milliy-university/IntelliJ IDEA
 */
@Service
public class SpeakerService {

    private final SpeakerRepository speakerRepository;
    private final StorageService storageService;

    public SpeakerService(SpeakerRepository speakerRepository, StorageService storageService) {
        this.speakerRepository = speakerRepository;
        this.storageService = storageService;
    }

    public Long create(SpeakerCreateDto dto) {
        if (speakerRepository.findByFullName(dto.getFullName()).isPresent()) {
            throw new GenericRuntimeException("Speaker already exist!", 400);
        }
        Speakers speakers = Speakers.builder()
                .fullName(dto.getFullName())
                .description(dto.getDescription())
                .status(Speakers.SpeakerStatus.valueOf(dto.getStatus()))
                .build();
        return speakerRepository.save(speakers).getId();
    }

    public void delete(Long id) {
        Optional<Speakers> optionalSpeakers = speakerRepository.findById(id);
        if (optionalSpeakers.isEmpty()) {
            throw new GenericNotFoundException("Speaker not found!", 404);
        }
        speakerRepository.deleteById(id);
    }

    public List<Speakers> getAll() {
        return speakerRepository.findAllByOrder();
    }

    public List<Speakers> getAllInvited() {
        return speakerRepository.getAllInvited();
    }

    public void uploadPhoto(MultipartFile file, Long id) {
        Optional<Speakers> optionalSpeakers = speakerRepository.findById(id);
        if (optionalSpeakers.isEmpty()) {
            throw new GenericNotFoundException("Speaker not found!", 404);
        }
        ImageMedia imageMedia = storageService.uploadPhoto(file);
        Speakers speakers = optionalSpeakers.get();
        speakers.setImagePath(imageMedia.getPath());
        speakerRepository.save(speakers);
    }
}
