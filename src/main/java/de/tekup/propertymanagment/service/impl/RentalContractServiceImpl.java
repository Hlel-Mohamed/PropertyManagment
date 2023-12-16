package de.tekup.propertymanagment.service.impl;

import de.tekup.propertymanagment.entity.RentalContract;
import de.tekup.propertymanagment.exception.RentalContractNotFoundException;
import de.tekup.propertymanagment.repository.RentalContractRepository;
import de.tekup.propertymanagment.service.RentalContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing Rental Contracts.
 */
@Slf4j
@Service
public class RentalContractServiceImpl implements RentalContractService {

    /**
     * The repository for Rental Contract entities.
     */
    @Autowired
    private RentalContractRepository rentalContractRepository;

    /**
     * Saves a given Rental Contract entity.
     * @param rentalContract the Rental Contract entity to save.
     * @return the saved Rental Contract entity.
     */
    @Override
    public RentalContract saveRentalContract(RentalContract rentalContract) {
        return rentalContractRepository.save(rentalContract);
    }

    /**
     * Finds a Rental Contract entity by its ID.
     * @param id the ID of the Rental Contract entity to find.
     * @return the found Rental Contract entity.
     * @throws RentalContractNotFoundException if no Rental Contract entity with the given ID could be found.
     */
    @Override
    public RentalContract findRentalContractById(Long id) {
        return rentalContractRepository.findById(id).orElseThrow(()->new RentalContractNotFoundException(id));
    }

    /**
     * Finds all Rental Contract entities.
     * @return a list of all Rental Contract entities.
     */
    @Override
    public List<RentalContract> findAllRentalContracts() {
        return rentalContractRepository.findAll();
    }

    /**
     * Finds all Rental Contract entities by Property ID.
     * @param id the ID of the Property entity.
     * @return a list of Rental Contract entities associated with the Property entity.
     */
    @Override
    public List<RentalContract> findAllRentalContractsByPropertyId(Long id) {
        return rentalContractRepository.findAllByPropertyId(id);
    }

    /**
     * Finds all Rental Contract entities by Owner ID.
     * @param id the ID of the Owner entity.
     * @return a list of Rental Contract entities associated with the Owner entity.
     */
    @Override
    public List<RentalContract> findAllRentalContractsByOwnerId(Long id) {
        return rentalContractRepository.findAllByOwnerId(id);
    }

    /**
     * Updates an existing Rental Contract entity with the data from a new Rental Contract entity.
     * @param id the ID of the existing Rental Contract entity to update.
     * @param newRentalContract the new Rental Contract entity with the data to update.
     * @return the updated Rental Contract entity.
     */
    @Override
    public RentalContract updateRentalContract(Long id, RentalContract newRentalContract) {
        RentalContract existingRentalContract = findRentalContractById(id);
        Optional.ofNullable(newRentalContract.getTenant()).ifPresent(existingRentalContract::setTenant);
        Optional.of(newRentalContract.getMonthlyRent()).ifPresent(existingRentalContract::setMonthlyRent);
        Optional.ofNullable(newRentalContract.getProperty()).ifPresent(existingRentalContract::setProperty);
        Optional.ofNullable(newRentalContract.getOwner()).ifPresent(existingRentalContract::setOwner);

        return rentalContractRepository.save(existingRentalContract);
    }

    /**
     * Deletes a Rental Contract entity by its ID.
     * @param id the ID of the Rental Contract entity to delete.
     */
    @Override
    public void deleteRentalContractById(Long id) {
        findRentalContractById(id);
        rentalContractRepository.deleteById(id);
    }
}
