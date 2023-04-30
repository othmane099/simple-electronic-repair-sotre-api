package com.odev.repairapp.repository;

import com.odev.repairapp.model.QuickReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface QuickReplyRepository extends JpaRepository<QuickReply, Long>, JpaSpecificationExecutor<QuickReply> {
    Page<QuickReply> findAll(Specification<QuickReply> specification, Pageable pageable);
    Optional<QuickReply> findByName(String name);
}
