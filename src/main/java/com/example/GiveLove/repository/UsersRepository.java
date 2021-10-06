package com.example.GiveLove.repository;

import com.example.GiveLove.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> , JpaSpecificationExecutor<Users> {
    Users findByUsername(String username);

    @Query(value = "select * from users where full_name like ?1" , nativeQuery = true)
    List<Users> search(String fullName);


    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET address=?1 , date_of_birth=?2 , email=?3, full_name=?4 ," +
            " gender = 1 , phone = ?5 WHERE id= ?6 " , nativeQuery = true)
    void updateProfile(String address , LocalDate dob , String email ,
                       String fullname , String phone , Long id);
}
