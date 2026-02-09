package ru.kudrin.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.kudrin.dto.CreateUserDto;
import ru.kudrin.entity.Gender;
import ru.kudrin.entity.Role;
import ru.kudrin.utils.LocalDateFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto>{
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();




    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreateUserDto userDto) {
       var result = new ValidationResult();
       if(!LocalDateFormatter.isValid(userDto.getBirthday())) {
           result.add(Error.of("invalid.birthday", "Invalid birthday"));
       }
       if(Gender.find(userDto.getGender()).isEmpty()) {
           result.add(Error.of("ivalid.gender", "Invalid gender"));
       }
        if(Role.find(userDto.getRole()).isEmpty()) {
            result.add(Error.of("ivalid.role", "Invalid role"));
        }
        return result;
    }
}
