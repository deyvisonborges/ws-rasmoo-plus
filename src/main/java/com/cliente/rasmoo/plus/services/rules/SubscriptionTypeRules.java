package com.cliente.rasmoo.plus.services.rules;

import com.cliente.rasmoo.plus.dtos.SubscriptionTypeDto;
import com.cliente.rasmoo.plus.models.SubscriptionTypeModel;

import java.util.List;

public interface SubscriptionTypeRules {
    List<SubscriptionTypeModel> findAll();
    SubscriptionTypeModel findById(Long id);
    SubscriptionTypeModel create(SubscriptionTypeDto dto);
    SubscriptionTypeModel update(Long id, SubscriptionTypeDto dto);
    void delete(Long id);
}
