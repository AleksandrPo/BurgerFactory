package burgerfactory.endpoints.auth.validation;

import burgerfactory.endpoints.auth.dto.UserDto;
import burgerfactory.endpoints.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private static final String CANNOT_BE_EMPTY = "CannotBeEmpty";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto) target;

        ValidationUtils.rejectIfEmpty(errors, USERNAME, CANNOT_BE_EMPTY);
        if(user.getUsername().length() < 4 || user.getUsername().length() > 16) {
            errors.rejectValue(USERNAME, "Size.userForm.username");
        }
        if(userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue(USERNAME, "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmpty(errors, PASSWORD, CANNOT_BE_EMPTY);
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", CANNOT_BE_EMPTY);
        if(user.getPassword().length() < 4 || user.getPassword().length() > 16) {
            errors.rejectValue(PASSWORD, "Size.userForm.password");
        }
        if(!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue(PASSWORD, "Diff.userForm.confirmPassword");
        }

        ValidationUtils.rejectIfEmpty(errors, "email", CANNOT_BE_EMPTY);
        if(!user.getEmail().contains("@")) {
            errors.rejectValue("email", "Wrong.userForm.email");
        }
        ValidationUtils.rejectIfEmpty(errors, "phone", CANNOT_BE_EMPTY);
    }
}
