package com.musyimi.repair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RepairRepository extends JpaRepository<Repair, Integer> {

   @Query
   boolean existsRepairByPhoneNumber(String phoneNumber);

   boolean existsRepairById(Integer id);


}
