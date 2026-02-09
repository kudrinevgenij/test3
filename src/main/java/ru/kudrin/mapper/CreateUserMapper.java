package ru.kudrin.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.kudrin.dto.CreateUserDto;
import ru.kudrin.entity.Gender;
import ru.kudrin.entity.Role;
import ru.kudrin.entity.User;
import ru.kudrin.utils.LocalDateFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<User, CreateUserDto> {
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    @Override
    public User mapFrom(CreateUserDto createUserDto) {
        return User.builder()
                .name(createUserDto.getName())
                .birthday(LocalDateFormatter.format(createUserDto.getBirthday()))
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .role(Role.valueOf(createUserDto.getRole()))
                .gender(Gender.valueOf(createUserDto.getGender()))
                .build();
    }

  public static CreateUserMapper getInstance() {
        return INSTANCE;
  }
}
