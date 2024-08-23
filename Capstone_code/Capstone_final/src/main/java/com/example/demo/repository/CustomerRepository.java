package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	//if you use class inplace of interface then in place of extends you have to use implements,then you have to implement every method of crudrepository 
	//if without implementing i can use it why should i go with class,That's why we had use here Interface
}
