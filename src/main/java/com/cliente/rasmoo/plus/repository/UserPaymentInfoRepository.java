package com.cliente.rasmoo.plus.repository;

import com.cliente.rasmoo.plus.models.UserPaymentInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentInfoRepository extends JpaRepository<UserPaymentInfoModel, Long> {
}
