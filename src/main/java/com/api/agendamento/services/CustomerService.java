package com.api.agendamento.services;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.api.agendamento.models.Customer;
import com.api.agendamento.repositories.CustomerRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.UUID;
import java.util.Optional;

@Service
public class CustomerService {

	final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Transactional
	public Customer save(Customer customerModel) {
		return this.customerRepository.save(customerModel);
	}

	public boolean existsByCpf(String cpf) {
		return this.customerRepository.existsByCpf(cpf);
	}

	public Page<Customer> findAll(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}
	
	public Optional<Customer> findById(UUID id) {
        return customerRepository.findById(id);
    }
	
	@Transactional
	public void delete(Customer customerMode) {
		this.customerRepository.delete(customerMode);
	}
	

}
