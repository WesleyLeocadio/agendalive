package com.spring.repository;

import com.spring.domain.LiveDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Repository
public interface LiveRepository extends JpaRepository<LiveDomain, Integer> {
    Page<LiveDomain> findByLiveDateAfterOrderByLiveDateAsc(Date date, Pageable pageable);
    Page<LiveDomain> findByLiveDateBeforeOrderByLiveDateDesc(Date date, Pageable pageable);
}
