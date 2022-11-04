package com.cliente.rasmoo.plus.repository;

import com.cliente.rasmoo.plus.models.SubscriptionTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionTypeRepository  extends JpaRepository<SubscriptionTypeModel, Long> {
}
