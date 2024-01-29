package com.api.agendamento.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import com.api.agendamento.DTO.CustomerDto;
import com.api.agendamento.services.CustomerService;
import com.api.agendamento.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/customers")
public class CustomerController {

	final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping
	public ResponseEntity<Object> saveCustomer(@RequestBody @Valid CustomerDto customerDto, Errors errors) {

		if (this.customerService.existsByCpf(customerDto.getCpf())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Este CPF já é usado por um cliente");
		}

		if (errors.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getAllErrors());
		}

		var customerModel = new Customer();

		BeanUtils.copyProperties(customerDto, customerModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerModel));

	}

	@GetMapping
	public ResponseEntity<Page<Customer>> getAll(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id) {
		Optional<Customer> customerModelOptional = customerService.findById(id);
		if (!customerModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(customerModelOptional.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remover(@PathVariable(value = "id") UUID id) {
		Optional<Customer> customerOptional = customerService.findById(id);
		if (!customerOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
		}
		customerService.delete(customerOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully.");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid CustomerDto customerDto) {
		Optional<Customer> customerOptional = customerService.findById(id);
		if (!customerOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
		}
		var customer = new Customer();
		BeanUtils.copyProperties(customerDto, customer);
		customer.setId(customerOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(customerService.save(customer));
	}

}
