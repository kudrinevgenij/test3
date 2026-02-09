package ru.kudrin.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.kudrin.dao.UserDao;
import ru.kudrin.dto.CreateUserDto;
import ru.kudrin.mapper.CreateUserMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private static final CreateUserMapper mapper = CreateUserMapper.getInstance();
    private static final UserDao userDao = UserDao.getInstance();

    public Integer create(CreateUserDto createUserDto) {
        var user = mapper.mapFrom(createUserDto);
        userDao.save(user);
        return user.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
