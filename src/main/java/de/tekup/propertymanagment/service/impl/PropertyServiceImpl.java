package de.tekup.propertymanagment.service.impl;

import de.tekup.propertymanagment.entity.Property;
import de.tekup.propertymanagment.exception.PropertyNotFoundException;
import de.tekup.propertymanagment.repository.PropertyRepository;
import de.tekup.propertymanagment.service.PropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing Properties.
 */
@Slf4j
@Service
public class PropertyServiceImpl implements PropertyService {

    /**
     * The repository for Property entities.
     */
    @Autowired
    private PropertyRepository propertyRepository;

    /**
     * Saves a given Property entity.
     * @param property the Property entity to save.
     * @return the saved Property entity.
     */
    @Override
    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    /**
     * Finds a Property entity by its ID.
     * @param id the ID of the Property entity to find.
     * @return the found Property entity.
     * @throws PropertyNotFoundException if no Property entity with the given ID could be found.
     */
    @Override
    public Property findPropertyById(Long id) {
        return propertyRepository.findById(id).orElseThrow(()->new PropertyNotFoundException(id));
    }

    /**
     * Finds all Property entities.
     * @return a list of all Property entities.
     */
    @Override
    public List<Property> findAllProperties() {
        return propertyRepository.findAll();
    }

    /**
     * Updates an existing Property entity with the data from a new Property entity.
     * @param id the ID of the existing Property entity to update.
     * @param newProperty the new Property entity with the data to update.
     * @return the updated Property entity.
     */
    @Override
    public Property updateProperty(Long id, Property newProperty) {
        Property existingProperty = findPropertyById(id);
        Optional.ofNullable(newProperty.getAddress()).ifPresent(existingProperty::setAddress);
        Optional.of(newProperty.getPrice()).ifPresent(existingProperty::setPrice);

        return propertyRepository.save(existingProperty);
    }

    /**
     * Deletes a Property entity by its ID.
     * @param id the ID of the Property entity to delete.
     */
    @Override
    public void deletePropertyById(Long id) {
        findPropertyById(id);
        propertyRepository.deleteById(id);
    }
}
