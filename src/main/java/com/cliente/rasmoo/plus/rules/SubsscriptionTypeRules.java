package com.cliente.rasmoo.plus.rules;

import com.cliente.rasmoo.plus.models.SubscriptionTypeModel;

import java.util.List;

public interface SubsscriptionTypeRules {
    List<SubscriptionTypeModel> findAll();
    SubscriptionTypeModel findById(Long id);
    SubscriptionTypeModel create(SubscriptionTypeModel subscriptionTypeModel);
    SubscriptionTypeModel update(Long id, SubscriptionTypeModel subscriptionTypeModel);
    void delete(Long id);
}
