package com.oenastech.repositary;

import com.oenastech.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {

    Client getClientByEmail(String email);
}
