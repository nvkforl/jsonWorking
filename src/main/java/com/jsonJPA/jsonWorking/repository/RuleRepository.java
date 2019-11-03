package com.jsonJPA.jsonWorking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsonJPA.jsonWorking.Entity.Rules;

@Repository
public interface RuleRepository extends JpaRepository<Rules,Integer> {

}
