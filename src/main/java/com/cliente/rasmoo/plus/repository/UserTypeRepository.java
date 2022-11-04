package com.cliente.rasmoo.plus.repository;

import com.cliente.rasmoo.plus.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserModel, Long> {
}
