package com.jsonJPA.jsonWorking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.jsonJPA.jsonWorking.Entity.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Integer> {

	@Query(value = "SELECT a FROM Auto a WHERE a.c_Id = :c_id AND a.sme_Name = :sme_name")
	List<Auto> getJsonFromAutoTBL(@Param("c_id") String c_id, @Param("sme_name") String sme_name);

}