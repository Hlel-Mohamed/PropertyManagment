package de.tekup.propertymanagment.service.impl;

import de.tekup.propertymanagment.entity.Owner;
import de.tekup.propertymanagment.exception.OwnerNotFoundException;
import de.tekup.propertymanagment.repository.OwnerRepository;
import de.tekup.propertymanagment.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing Owners.
 */
@Slf4j
@Service
public class OwnerServiceImpl implements OwnerService {

    /**
     * The repository for Owner entities.
     */
    @Autowired
    private OwnerRepository ownerRepository;

    /**
     * Saves a given Owner entity.
     * @param owner the Owner entity to save.
     * @return the saved Owner entity.
     */
    @Override
    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    /**
     * Finds an Owner entity by its ID.
     * @param id the ID of the Owner entity to find.
     * @return the found Owner entity.
     * @throws OwnerNotFoundException if no Owner entity with the given ID could be found.
     */
    @Override
    public Owner findOwnerById(Long id) {
        return ownerRepository.findById(id).orElseThrow(()->new OwnerNotFoundException(id));
    }

    /**
     * Finds all Owner entities.
     * @return a list of all Owner entities.
     */
    @Override
    public List<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }

    /**
     * Updates an existing Owner entity with the data from a new Owner entity.
     * @param id the ID of the existing Owner entity to update.
     * @param newOwner the new Owner entity with the data to update.
     * @return the updated Owner entity.
     */
    @Override
    public Owner updateOwner(Long id, Owner newOwner) {
        Owner existingOwner = findOwnerById(id);
        Optional.ofNullable(newOwner.getName()).ifPresent(existingOwner::setName);
        Optional.ofNullable(newOwner.getContactInformation()).ifPresent(existingOwner::setContactInformation);

        return ownerRepository.save(existingOwner);
    }

    /**
     * Deletes an Owner entity by its ID.
     * @param id the ID of the Owner entity to delete.
     */
    @Override
    public void deleteOwnerById(Long id) {
        findOwnerById(id);
        ownerRepository.deleteById(id);
    }
}