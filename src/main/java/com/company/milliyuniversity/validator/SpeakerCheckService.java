package com.company.milliyuniversity.validator;

import com.company.milliyuniversity.domains.Speakers;
import com.company.milliyuniversity.exceptions.GenericNotFoundException;
import com.company.milliyuniversity.exceptions.GenericRuntimeException;
import com.company.milliyuniversity.repository.SpeakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 26/02/23 Sunday 19:09
 * milliy-university/IntelliJ IDEA
 */
@Service
@RequiredArgsConstructor
public class SpeakerCheckService {
    private final SpeakerRepository speakerRepository;

    public void checkByName(String fullName) {
        speakerRepository.findByFullName(fullName).orElseThrow(() -> new GenericRuntimeException("Speaker already exist!", 400));
    }

    public Speakers checkById(Long id) {
        return speakerRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Speaker not found!", 404));
    }
}
