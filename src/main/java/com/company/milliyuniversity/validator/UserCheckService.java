package com.company.milliyuniversity.validator;

import com.company.milliyuniversity.domains.auth.AuthUser;
import com.company.milliyuniversity.dtos.UserRegisterDTO;
import com.company.milliyuniversity.dtos.auth.AuthUserUpdateDto;
import com.company.milliyuniversity.exceptions.GenericRuntimeException;
import com.company.milliyuniversity.repository.AuthUserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 24/02/23 Friday 19:13
 * milliy-university/IntelliJ IDEA
 */
@Service
@RequiredArgsConstructor
public class UserCheckService {
    private final AuthUserRepository authUserRepository;

    public void checkUsername(@NonNull String username) {
        Pattern pattern = Pattern.compile("^[a-z0-9]{6,8}$");
        if (!pattern.matcher(username).matches()) {
            throw new GenericRuntimeException("Username must be valid!", 400);
        }
        authUserRepository.findByUsername(username).ifPresent(authUser -> {
            throw new GenericRuntimeException("Username already exist!", 400);
        });
    }

    public void checkPassword(@NonNull String password) {
        Pattern pattern = Pattern.compile("((\\d*)([a-z]*){6,8})");
        if (!pattern.matcher(password).matches()) {
            throw new GenericRuntimeException("Password must be valid!", 400);
        }
    }

    public void checkTelephone(@NonNull String telephone) {
        Pattern pattern = Pattern.compile("^\\+998([- ])?(90|91|93|94|95|98|99|33|97|71|88)([- ])?(\\d{3})([- ])?(\\d{2})([- ])?(\\d{2})$");
        if (!pattern.matcher(telephone).matches()) {
            throw new GenericRuntimeException("Telephone must be valid!", 400);
        }
        authUserRepository.findByTelephone(telephone).ifPresent(authUser -> {
            throw new GenericRuntimeException("Telephone already exist!", 400);
        });
    }

    public void checkEmail(@NonNull String email) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (!pattern.matcher(email).matches()) {
            throw new GenericRuntimeException("Email must be valid!", 400);
        }
        authUserRepository.findByEmail(email).ifPresent(authUser -> {
            throw new GenericRuntimeException("Email already exist!", 400);
        });
    }

    public void checkById(@NonNull Long id) {
        authUserRepository.findById(id).orElseThrow(() -> {
            throw new GenericRuntimeException("User did not exist!", 400);
        });
    }

    public void registerCheck(UserRegisterDTO dto) {
        checkPassword(dto.getPassword());
        checkEmail(dto.getEmail());
        checkTelephone(dto.getTelephone());
        checkUsername(dto.getUsername());
    }

    public AuthUser updateCheck(AuthUserUpdateDto dto) {
        AuthUser authUser = authUserRepository.findByUsername(dto.getUsername()).orElseThrow(() -> {
            throw new GenericRuntimeException("User did not exist!", 400);
        });
        if (!authUser.getTelephone().equals(dto.getTelephone())) {
            checkTelephone(dto.getTelephone());
        }
        if (!authUser.getEmail().equals(dto.getEmail())) {
            checkEmail(dto.getEmail());
        }
        return authUser;
    }
}
