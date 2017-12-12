package com.maleic.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record,Integer> {
    List<Record> findByDid(Integer did);

    List<Record> findByDidIn(List<Integer> dids);
}
