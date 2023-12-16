package de.tekup.propertymanagment.service;

import de.tekup.propertymanagment.entity.Property;

import java.util.List;

public interface PropertyService {
    Property saveProperty(Property property);
    Property findPropertyById(Long id);
    List<Property> findAllProperties();
    Property updateProperty(Long id, Property newProperty);
    void deletePropertyById(Long id);
}
