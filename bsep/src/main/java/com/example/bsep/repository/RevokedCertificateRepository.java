package com.example.bsep.repository;

import com.example.bsep.model.RevokedCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevokedCertificateRepository extends JpaRepository<RevokedCertificate, Long> {

    RevokedCertificate findByAlias(String alias);
}
