package com.company.milliyuniversity.service;

import com.company.milliyuniversity.domains.Speakers;
import com.company.milliyuniversity.dtos.SpeakerCreateDto;
import com.company.milliyuniversity.mapper.SpeakerMapper;
import com.company.milliyuniversity.repository.SpeakerRepository;
import com.company.milliyuniversity.validator.SpeakerCheckService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 08/02/23 Wednesday 10:41
 * milliy-university/IntelliJ IDEA
 */
@Service
public class SpeakerService {

    private final SpeakerRepository speakerRepository;
    private final StorageService storageService;
    private final SpeakerCheckService checkService;
    private final SpeakerMapper mapper;

    public SpeakerService(SpeakerRepository speakerRepository, StorageService storageService, SpeakerCheckService checkService, SpeakerMapper mapper) {
        this.speakerRepository = speakerRepository;
        this.storageService = storageService;
        this.checkService = checkService;
        this.mapper = mapper;
    }

    public Long create(@NonNull SpeakerCreateDto dto) {
        checkService.checkByName(dto.getFullName());
        return speakerRepository.save(mapper.fromDto(dto)).getId();
    }

    public void delete(@NonNull Long id) {
        checkService.checkById(id);
        speakerRepository.deleteById(id);
    }

    public List<Speakers> getAll() {
        return speakerRepository.findAllByOrder();
    }

    public List<Speakers> getAllInvited() {
        return speakerRepository.getAllInvited();
    }

    public void uploadPhoto(@NonNull MultipartFile file, @NonNull Long id) {
        Speakers speakers = checkService.checkById(id);
        speakers.setImagePath(storageService.uploadPhoto(file).getPath());
        speakerRepository.save(speakers);
    }

    public void getImage(String img, HttpServletResponse resp) {
        ServletOutputStream outputStream;
        try {
            outputStream = resp.getOutputStream();
            Path path = Path.of(img);
            Files.copy(path, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
