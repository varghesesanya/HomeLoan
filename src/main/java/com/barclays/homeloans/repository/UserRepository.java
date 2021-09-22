package com.barclays.homeloans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.homeloans.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}