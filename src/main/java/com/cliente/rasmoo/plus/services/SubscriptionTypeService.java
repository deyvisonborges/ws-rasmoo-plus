package com.cliente.rasmoo.plus.services;

import com.cliente.rasmoo.plus.dtos.SubscriptionTypeDto;
import com.cliente.rasmoo.plus.exceptions.BadRequestException;
import com.cliente.rasmoo.plus.exceptions.NotFoundException;
import com.cliente.rasmoo.plus.models.SubscriptionTypeModel;
import com.cliente.rasmoo.plus.repositories.SubscriptionTypeRepository;
import com.cliente.rasmoo.plus.services.rules.SubscriptionTypeRules;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionTypeService implements SubscriptionTypeRules {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    public SubscriptionTypeService(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    public List<SubscriptionTypeModel> findAll() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionTypeModel findById(Long id) {
        Optional<SubscriptionTypeModel> optionalSubscriptionTypeModel = subscriptionTypeRepository.findById(id);
        if(optionalSubscriptionTypeModel.isEmpty()) {
            throw new NotFoundException("SubscriptionType não encontrado");
        }
        return optionalSubscriptionTypeModel.get();
    }

    @Override
    public SubscriptionTypeModel create(SubscriptionTypeDto dto) {
        if(Objects.nonNull(dto.getId())) {
            throw new BadRequestException("Id deve ser nulo");
        }
        return subscriptionTypeRepository.save(
            SubscriptionTypeModel
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .accessMonth(dto.getAccessMonth())
                .price(dto.getPrice())
                .productKey(dto.getProductKey())
                .build());
    }

    @Override
    public SubscriptionTypeModel update(Long id, SubscriptionTypeModel subscriptionTypeModel) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
