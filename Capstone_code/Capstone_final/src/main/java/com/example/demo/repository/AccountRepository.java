package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import com.example.demo.entities.Account;


@Repository                                                                                                                       //It indicates that an annotated class is repository, which provides mechanism for storage,retriaval,search,update,delete operation of object
public interface AccountRepository extends JpaRepository<Account, Integer>{//integer is prmary key datatype
	
	
	//if you use class inplace of interface then in place of extends you have to use implements,then you have to implement every method of crudrepository 
	//if without implementing i can use it why should i go with class,That's why we had use here Interface
	
	
	@Query("select s from Account s where s.acctype = ?1")                                                        //userdefined method
   Account getByacctype(String acctype);
	
	
	
	
}
