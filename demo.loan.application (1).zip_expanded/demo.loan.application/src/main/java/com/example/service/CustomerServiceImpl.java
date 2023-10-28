package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beans.Customer;
import com.example.exceptions.ProfileNotFoundException;
import com.npci.dao.CustomerDao;

import jakarta.transaction.Transactional;

	@Service
	public class CustomerServiceImpl implements CustomerService {

		@Autowired
		private CustomerDao customerdao;

		@Override
		@Transactional
		public Customer createCustomer(Customer customer) {
			return customerdao.save(customer);
		}

		@Override
		public Customer getCustomer(String email_id) {
			Customer customer = customerdao.getCustomer(email_id);
			return customer;
		}

		@Override
		public Customer login(String email_id, String password) throws ProfileNotFoundException {
			Customer customer = customerdao.getCustomer(email_id);
			if (customer.getPassword().equals(password)) {
				return customer;
			}
			throw new ProfileNotFoundException("Customer not found with an email-id " + email_id);

		}

	}
