package com.musyimi.repair;

import com.musyimi.exception.DuplicateResourceException;
import com.musyimi.exception.RequestValidationException;
import com.musyimi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {

    private final RepairDao repairDao;

    public RepairService(@Qualifier("jdbc") RepairDao repairDao) {
        this.repairDao = repairDao;
    }

    public List<Repair> getAllRepairs() {
        return repairDao.selectAllRepairs();
    }

    public Repair getRepair(Integer id) {
        return repairDao.selectRepairById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Repair with id [%s] is not found".formatted(id))
                );
    }

    public void addRepair(
            RepairRegistrationRequest repairRegistrationRequest) {

        String phoneNumber = repairRegistrationRequest.phoneNumber();

        if (repairDao.existsPersonWithPhoneNumber(repairRegistrationRequest.phoneNumber())) {
            throw new DuplicateResourceException(
                    "Phone Number already in use"
            );
        }
        Repair repair = new Repair(
                repairRegistrationRequest.name(),
                repairRegistrationRequest.title(),
                repairRegistrationRequest.brand(),
                repairRegistrationRequest.issue(),
                repairRegistrationRequest.phoneNumber()
        );
        repairDao.insertRepair(repair);
    }

    public void deleteById(Integer id) {
        if(!repairDao.existsRepairWithId(id)) {
            throw new ResourceNotFoundException(
                    "Repair with id [%s] not found".formatted(id)
            );
        }
        repairDao.deleteById(id);
    }

     public void updateRepair(
             Integer Id,
             RepairUpdateRequest updateRequest
     ) {
        Repair repair = getRepair(Id);

        boolean changes = false;

        if(updateRequest.name() != null && !updateRequest.name().equals(repair.getName())){
            repair.setName(updateRequest.name());
            changes = true;
        }

         if(updateRequest.phoneNumber() != null && !updateRequest.phoneNumber().equals(repair.getphoneNumber())){
             if (repairDao.existsPersonWithPhoneNumber(updateRequest.phoneNumber())) {
                 throw new DuplicateResourceException(
                         "Phone Number Already in use"
                 );
             }

             repair.setphoneNumber(updateRequest.phoneNumber());
             changes = true;
         }

         if(updateRequest.brand() != null && !updateRequest.brand().equals(repair.getBrand())){
             repair.setBrand(updateRequest.brand());
             changes = true;
         }

         if(updateRequest.title() != null && !updateRequest.title().equals(repair.getTitle())){
             repair.setTitle(updateRequest.title());
             changes = true;
         }

         if(updateRequest.issue() != null && !updateRequest.issue().equals(repair.getIssue())){
             repair.setIssue(updateRequest.issue());
             changes = true;
         }

         if (!changes) {
             throw new RequestValidationException("No data changes found");
         }

         repairDao.updateRepair(repair);

     }
}
