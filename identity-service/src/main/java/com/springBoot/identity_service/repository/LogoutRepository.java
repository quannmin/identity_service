package com.springBoot.identity_service.repository;

import com.springBoot.identity_service.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogoutRepository extends JpaRepository<InvalidatedToken, String> {

}
