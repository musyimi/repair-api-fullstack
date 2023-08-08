package com.musyimi.repair;

import java.util.List;
import java.util.Optional;

public interface RepairDao {
    List<Repair> selectAllRepairs();
    Optional<Repair> selectRepairById(Integer id);
    void insertRepair(Repair repair);
    boolean existsPersonWithPhoneNumber(String phoneNumber);
    boolean existsRepairWithId(Integer id);
    void deleteById(Integer id);
    void updateRepair(Repair update);
}
