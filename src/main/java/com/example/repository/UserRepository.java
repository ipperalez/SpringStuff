package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Account;

@Repository("accountRepository")
public interface UserRepository extends JpaRepository<Account, Long> {
	 Account findByEmail(String email);
}
