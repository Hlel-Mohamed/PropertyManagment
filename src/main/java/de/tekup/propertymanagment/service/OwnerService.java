package de.tekup.propertymanagment.service;

import de.tekup.propertymanagment.entity.Owner;

import java.util.List;

public interface OwnerService {
    Owner saveOwner(Owner owner);
    Owner findOwnerById(Long id);
    List<Owner> findAllOwners();
    Owner updateOwner(Long id, Owner newOwner);
    void deleteOwnerById(Long id);
}
