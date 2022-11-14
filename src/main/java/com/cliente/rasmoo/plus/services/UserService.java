package com.cliente.rasmoo.plus.services;

import com.cliente.rasmoo.plus.dtos.UserDto;
import com.cliente.rasmoo.plus.exceptions.BadRequestException;
import com.cliente.rasmoo.plus.exceptions.NotFoundException;
import com.cliente.rasmoo.plus.mappers.UserMapper;
import com.cliente.rasmoo.plus.models.UserModel;
import com.cliente.rasmoo.plus.models.UserTypeModel;
import com.cliente.rasmoo.plus.repositories.UserRepository;
import com.cliente.rasmoo.plus.repositories.UserTypeRepository;
import com.cliente.rasmoo.plus.services.rules.UserServiceRules;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserServiceRules {
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    // ponto de injeção via construtor
    public UserService(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserModel create(UserDto dto) {
        if (Objects.nonNull(dto.getId())) {
            throw new BadRequestException("Id deve ser nulo");
        }

        var userTypeOpt = userTypeRepository.findById(dto.getUserTypeId());

        if (userTypeOpt.isEmpty()) {
            throw new NotFoundException("userTypId não encontrado");
        }

        UserTypeModel userType = userTypeOpt.get();
        UserModel user = UserMapper.fromDtoToEntity(dto, userType, null);

        return userRepository.save(user);
    }
}
