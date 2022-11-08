package com.cliente.rasmoo.plus.services.rules;

import com.cliente.rasmoo.plus.dtos.SubscriptionTypeDto;
import com.cliente.rasmoo.plus.models.SubscriptionTypeModel;

import java.util.List;

public interface SubscriptionTypeRules {
    List<SubscriptionTypeModel> findAll();
    SubscriptionTypeModel findById(Long id);
    SubscriptionTypeModel create(SubscriptionTypeDto subscriptionTypeDto);
    SubscriptionTypeModel update(Long id, SubscriptionTypeModel subscriptionTypeModel);
    void delete(Long id);
}
