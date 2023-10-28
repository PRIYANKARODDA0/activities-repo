package com.example.service;

import com.example.beans.Customer;
import com.example.exceptions.ProfileNotFoundException;

public interface CustomerService {


		public Customer getCustomer(String email_id);
		public Customer login(String email_id, String password) throws ProfileNotFoundException;
		public Customer createCustomer(Customer customer);
		
	}
