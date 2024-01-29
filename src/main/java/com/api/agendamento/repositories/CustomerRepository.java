package com.api.agendamento.repositories;

import java.util.UUID;
import com.api.agendamento.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,UUID>{
   boolean existsByCpf(String cpf);
}
