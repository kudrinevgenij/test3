package ru.kudrin.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.kudrin.dao.UserDao;
import ru.kudrin.dto.CreateUserDto;
import ru.kudrin.exception.ValidationException;
import ru.kudrin.mapper.CreateUserMapper;
import ru.kudrin.validator.CreateUserValidator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private static final CreateUserMapper mapper = CreateUserMapper.getInstance();
    private static final UserDao userDao = UserDao.getInstance();

    private static final CreateUserValidator createUserValidator= CreateUserValidator.getInstance();

    public Integer create(CreateUserDto createUserDto) {
        var validationResult = createUserValidator.isValid(createUserDto);
        if(!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var user = mapper.mapFrom(createUserDto);
        userDao.save(user);
        return user.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
