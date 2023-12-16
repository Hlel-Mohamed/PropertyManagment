package de.tekup.propertymanagment.service;

import de.tekup.propertymanagment.entity.Property;
import de.tekup.propertymanagment.exception.PropertyNotFoundException;
import de.tekup.propertymanagment.repository.PropertyRepository;
import de.tekup.propertymanagment.service.impl.PropertyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PropertyServiceImplTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void savePropertyShouldReturnSavedProperty() {
        Property property = new Property();
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        Property savedProperty = propertyService.saveProperty(property);

        assertNotNull(savedProperty);
        verify(propertyRepository, times(1)).save(property);
    }

    @Test
    public void findPropertyByIdShouldReturnPropertyWhenExists() {
        Property property = new Property();
        when(propertyRepository.findById(anyLong())).thenReturn(Optional.of(property));

        Property foundProperty = propertyService.findPropertyById(1L);

        assertNotNull(foundProperty);
        verify(propertyRepository, times(1)).findById(1L);
    }

    @Test
    public void findPropertyByIdShouldThrowExceptionWhenDoesNotExist() {
        when(propertyRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PropertyNotFoundException.class, () -> propertyService.findPropertyById(1L));
        verify(propertyRepository, times(1)).findById(1L);
    }

    @Test
    public void findAllPropertiesShouldReturnAllProperties() {
        List<Property> properties = Arrays.asList(new Property(), new Property());
        when(propertyRepository.findAll()).thenReturn(properties);

        List<Property> foundProperties = propertyService.findAllProperties();

        assertEquals(2, foundProperties.size());
        verify(propertyRepository, times(1)).findAll();
    }

    @Test
    public void updatePropertyShouldUpdateAndReturnPropertyWhenExists() {
        Property existingProperty = new Property();
        Property newProperty = new Property();
        newProperty.setAddress("New Address");
        newProperty.setPrice(1000.0);
        when(propertyRepository.findById(anyLong())).thenReturn(Optional.of(existingProperty));
        when(propertyRepository.save(any(Property.class))).thenReturn(newProperty);

        Property updatedProperty = propertyService.updateProperty(1L, newProperty);

        assertEquals("New Address", updatedProperty.getAddress());
        assertEquals(1000.0, updatedProperty.getPrice());
        verify(propertyRepository, times(1)).findById(1L);
        verify(propertyRepository, times(1)).save(existingProperty);
    }

    @Test
    public void deletePropertyByIdShouldDeletePropertyWhenExists() {
        Property property = new Property();
        when(propertyRepository.findById(anyLong())).thenReturn(Optional.of(property));
        doNothing().when(propertyRepository).deleteById(anyLong());

        propertyService.deletePropertyById(1L);

        verify(propertyRepository, times(1)).findById(1L);
        verify(propertyRepository, times(1)).deleteById(1L);
    }
}
