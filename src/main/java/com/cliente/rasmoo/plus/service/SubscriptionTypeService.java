package com.cliente.rasmoo.plus.service;

import com.cliente.rasmoo.plus.exceptions.NotFoundException;
import com.cliente.rasmoo.plus.models.SubscriptionTypeModel;
import com.cliente.rasmoo.plus.repository.SubscriptionTypeRepository;
import com.cliente.rasmoo.plus.rules.SubsscriptionTypeRules;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionTypeService implements SubsscriptionTypeRules {

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
    public SubscriptionTypeModel create(SubscriptionTypeModel subscriptionTypeModel) {
        return null;
    }

    @Override
    public SubscriptionTypeModel update(Long id, SubscriptionTypeModel subscriptionTypeModel) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
