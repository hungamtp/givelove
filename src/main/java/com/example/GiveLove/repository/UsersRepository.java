package com.example.GiveLove.repository;

import com.example.GiveLove.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> , JpaSpecificationExecutor<Users> {
    Users findByUsername(String username);
}
