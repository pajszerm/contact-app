package com.example.contactapp.repositories;

import com.example.contactapp.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {

    @Query("SELECT u FROM UserInfo u WHERE u.username = :username")
    UserInfo findUserInfoByUsername(@Param("username") String username);

    @Query("SELECT u FROM UserInfo u WHERE u.email = :email")
    UserInfo findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM UserInfo u WHERE u.tajNumber = :tajNumber")
    UserInfo findUserByTajNumber(@Param("tajNumber") String tajNumber);

    @Query("SELECT u FROM UserInfo u WHERE u.taxNumber = :taxNumber")
    UserInfo findUserByTaxNumber(@Param("taxNumber") String taxNumber);
}
