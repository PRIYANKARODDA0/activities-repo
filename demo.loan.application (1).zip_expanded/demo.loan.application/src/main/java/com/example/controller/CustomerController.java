package com.example.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Customer;
import com.example.exceptions.ProfileNotFoundException;
import com.example.service.CustomerService;

	@CrossOrigin(origins = { "*" })
	@RequestMapping("/api/customer")
	@RestController
	public class CustomerController {

		@Autowired
		private CustomerService customerService;

		@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> store(@RequestBody Customer customer) {
			Customer c = customerService.createCustomer(customer);
			return ResponseEntity.status(200).body(c);
		}

		@GetMapping(path = "/login/{emailId}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> login(@PathVariable("emailId") String emailId,
				@PathVariable("password") String password) {
			try {
				return ResponseEntity.status(200).body(customerService.login(emailId, password));
			} catch (ProfileNotFoundException e) {
				Map<String, String> map = new HashMap<>();
				map.put("error", e.getMessage());
				return ResponseEntity.status(404).body(map);
			}
		}

		@GetMapping(path = "/{emailId}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> fetch(@PathVariable("emailId") String emailId) {
			return ResponseEntity.status(200).body(customerService.getCustomer(emailId));
		}

	}
