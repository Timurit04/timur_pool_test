package com.timur_ufanet.pool.repository;

import com.timur_ufanet.pool.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
