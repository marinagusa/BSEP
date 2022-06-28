package com.example.bsep.repository;

import com.example.bsep.model.InvalidJwt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidJwtRepository extends JpaRepository<InvalidJwt, Long> {

    InvalidJwt findByJwt(String jwt);
}
