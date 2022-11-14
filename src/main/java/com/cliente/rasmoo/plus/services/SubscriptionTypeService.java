package com.cliente.rasmoo.plus.services;

import com.cliente.rasmoo.plus.controllers.SubscriptionTypeController;
import com.cliente.rasmoo.plus.dtos.SubscriptionTypeDto;
import com.cliente.rasmoo.plus.exceptions.BadRequestException;
import com.cliente.rasmoo.plus.exceptions.NotFoundException;
import com.cliente.rasmoo.plus.mappers.SubscriptionTypeMapper;
import com.cliente.rasmoo.plus.models.SubscriptionTypeModel;
import com.cliente.rasmoo.plus.repositories.SubscriptionTypeRepository;
import com.cliente.rasmoo.plus.services.rules.SubscriptionTypeRules;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionTypeService implements SubscriptionTypeRules {

    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

    public SubscriptionTypeService(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    public List<SubscriptionTypeModel> findAll() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionTypeModel findById(Long id) {
        return getSubscriptionTypeModel(id)
        .add(WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder
                .methodOn(SubscriptionTypeController.class)
                .findById(id))
                .withSelfRel()
        )
        .add(WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder
                .methodOn(SubscriptionTypeController.class)
                .update(id, new SubscriptionTypeDto()))
                .withRel(UPDATE)
        )
        .add(WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder
                .methodOn(SubscriptionTypeController.class)
                .delete(id))
                .withRel(DELETE)
        );
    }

    @Override
    public SubscriptionTypeModel create(SubscriptionTypeDto dto) {
        if(Objects.nonNull(dto.getId())) {
            throw new BadRequestException("Id deve ser nulo");
        }
        return subscriptionTypeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(dto));
    }

    @Override
    public SubscriptionTypeModel update(Long id, SubscriptionTypeDto dto) {
       getSubscriptionTypeModel(id);
       dto.setId(id);
       return subscriptionTypeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(dto));
    }

    @Override
    public void delete(Long id) {
        getSubscriptionTypeModel(id);
        subscriptionTypeRepository.deleteById(id);
    }

    private SubscriptionTypeModel getSubscriptionTypeModel(Long id) {
        Optional<SubscriptionTypeModel> optionalSubscriptionTypeModel = subscriptionTypeRepository.findById(id);
        if(optionalSubscriptionTypeModel.isEmpty()) {
            throw new NotFoundException("SubscriptionType não encontrado");
        }
        return optionalSubscriptionTypeModel.get();
    }
}
