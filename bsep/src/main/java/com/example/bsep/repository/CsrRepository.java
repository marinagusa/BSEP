package com.example.bsep.repository;

import com.example.bsep.model.Csr;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CsrRepository extends JpaRepository<Csr, Long> {

    List<Csr> findByCommonName(String cn);
}
