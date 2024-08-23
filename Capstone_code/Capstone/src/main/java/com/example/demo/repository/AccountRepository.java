package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.demo.entities.Account;


@Repository                                                                                                                       //It indicates that an annotated class is repository, which provides mechanism for storage,retriaval,search,update,delete operation of object
public interface AccountRepository extends JpaRepository<Account, Integer>{//integer is prmary key datatype
	
	@Query("select s from Account s where s.acctype = ?1")                                                        //userdefined method
   Account getByacctype(String acctype);
	
	
	
	
}
