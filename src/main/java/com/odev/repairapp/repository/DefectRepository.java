package com.odev.repairapp.repository;

import com.odev.repairapp.model.Defect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface DefectRepository extends JpaRepository<Defect, Long>, JpaSpecificationExecutor<Defect> {
    Page<Defect> findAll(Specification<Defect> specification, Pageable pageable);
    Optional<Defect> findByTitle(String title);
}
