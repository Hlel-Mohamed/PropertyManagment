package de.tekup.propertymanagment.service;

import de.tekup.propertymanagment.entity.RentalContract;

import java.util.List;

public interface RentalContractService {
    RentalContract saveRentalContract(RentalContract rentalContract);
    RentalContract findRentalContractById(Long id);
    List<RentalContract> findAllRentalContracts();
    List<RentalContract> findAllRentalContractsByPropertyId(Long id);
    List<RentalContract> findAllRentalContractsByOwnerId(Long id);
    RentalContract updateRentalContract(Long id, RentalContract newRentalContract);
    void deleteRentalContractById(Long id);
}
