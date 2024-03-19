package com.example.contactapp.repositories;

import com.example.contactapp.domain.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

    @Query("SELECT p FROM PhoneNumber p WHERE p.number =: phoneNumber")
    PhoneNumber findPhoneNumberByNumber(@Param("phoneNumber")Integer phoneNumber);
}
