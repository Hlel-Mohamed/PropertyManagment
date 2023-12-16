package de.tekup.propertymanagment.service;

import de.tekup.propertymanagment.entity.Owner;
import de.tekup.propertymanagment.exception.OwnerNotFoundException;
import de.tekup.propertymanagment.repository.OwnerRepository;
import de.tekup.propertymanagment.service.impl.OwnerServiceImpl;
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

public class OwnerServiceImplTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveOwnerShouldReturnSavedOwner() {
        Owner owner = new Owner();
        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);

        Owner savedOwner = ownerService.saveOwner(owner);

        assertNotNull(savedOwner);
        verify(ownerRepository, times(1)).save(owner);
    }

    @Test
    public void findOwnerByIdShouldReturnOwnerWhenExists() {
        Owner owner = new Owner();
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));

        Owner foundOwner = ownerService.findOwnerById(1L);

        assertNotNull(foundOwner);
        verify(ownerRepository, times(1)).findById(1L);
    }

    @Test
    public void findOwnerByIdShouldThrowExceptionWhenDoesNotExist() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OwnerNotFoundException.class, () -> ownerService.findOwnerById(1L));
        verify(ownerRepository, times(1)).findById(1L);
    }

    @Test
    public void findAllOwnersShouldReturnAllOwners() {
        List<Owner> owners = Arrays.asList(new Owner(), new Owner());
        when(ownerRepository.findAll()).thenReturn(owners);

        List<Owner> foundOwners = ownerService.findAllOwners();

        assertEquals(2, foundOwners.size());
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    public void updateOwnerShouldUpdateAndReturnOwnerWhenExists() {
        Owner existingOwner = new Owner();
        Owner newOwner = new Owner();
        newOwner.setName("New Name");
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(existingOwner));
        when(ownerRepository.save(any(Owner.class))).thenReturn(newOwner);

        Owner updatedOwner = ownerService.updateOwner(1L, newOwner);

        assertEquals("New Name", updatedOwner.getName());
        verify(ownerRepository, times(1)).findById(1L);
        verify(ownerRepository, times(1)).save(existingOwner);
    }

    @Test
    public void deleteOwnerByIdShouldDeleteOwnerWhenExists() {
        Owner owner = new Owner();
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));
        doNothing().when(ownerRepository).deleteById(anyLong());

        ownerService.deleteOwnerById(1L);

        verify(ownerRepository, times(1)).findById(1L);
        verify(ownerRepository, times(1)).deleteById(1L);
    }
}
