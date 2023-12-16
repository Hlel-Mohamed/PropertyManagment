package de.tekup.propertymanagment.service;

import de.tekup.propertymanagment.entity.RentalContract;
import de.tekup.propertymanagment.exception.RentalContractNotFoundException;
import de.tekup.propertymanagment.repository.RentalContractRepository;
import de.tekup.propertymanagment.service.impl.RentalContractServiceImpl;
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

public class RentalContractServiceImplTest {

    @Mock
    private RentalContractRepository rentalContractRepository;

    @InjectMocks
    private RentalContractServiceImpl rentalContractService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveRentalContractShouldReturnSavedRentalContract() {
        RentalContract rentalContract = new RentalContract();
        when(rentalContractRepository.save(any(RentalContract.class))).thenReturn(rentalContract);

        RentalContract savedRentalContract = rentalContractService.saveRentalContract(rentalContract);

        assertNotNull(savedRentalContract);
        verify(rentalContractRepository, times(1)).save(rentalContract);
    }

    @Test
    public void findRentalContractByIdShouldReturnRentalContractWhenExists() {
        RentalContract rentalContract = new RentalContract();
        when(rentalContractRepository.findById(anyLong())).thenReturn(Optional.of(rentalContract));

        RentalContract foundRentalContract = rentalContractService.findRentalContractById(1L);

        assertNotNull(foundRentalContract);
        verify(rentalContractRepository, times(1)).findById(1L);
    }

    @Test
    public void findRentalContractByIdShouldThrowExceptionWhenDoesNotExist() {
        when(rentalContractRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RentalContractNotFoundException.class, () -> rentalContractService.findRentalContractById(1L));
        verify(rentalContractRepository, times(1)).findById(1L);
    }

    @Test
    public void findAllRentalContractsShouldReturnAllRentalContracts() {
        List<RentalContract> rentalContracts = Arrays.asList(new RentalContract(), new RentalContract());
        when(rentalContractRepository.findAll()).thenReturn(rentalContracts);

        List<RentalContract> foundRentalContracts = rentalContractService.findAllRentalContracts();

        assertEquals(2, foundRentalContracts.size());
        verify(rentalContractRepository, times(1)).findAll();
    }

    @Test
    public void updateRentalContractShouldUpdateAndReturnRentalContractWhenExists() {
        RentalContract existingRentalContract = new RentalContract();
        RentalContract newRentalContract = new RentalContract();
        when(rentalContractRepository.findById(anyLong())).thenReturn(Optional.of(existingRentalContract));
        when(rentalContractRepository.save(any(RentalContract.class))).thenReturn(newRentalContract);

        RentalContract updatedRentalContract = rentalContractService.updateRentalContract(1L, newRentalContract);

        assertNotNull(updatedRentalContract);
        verify(rentalContractRepository, times(1)).findById(1L);
        verify(rentalContractRepository, times(1)).save(existingRentalContract);
    }

    @Test
    public void deleteRentalContractByIdShouldDeleteRentalContractWhenExists() {
        RentalContract rentalContract = new RentalContract();
        when(rentalContractRepository.findById(anyLong())).thenReturn(Optional.of(rentalContract));
        doNothing().when(rentalContractRepository).deleteById(anyLong());

        rentalContractService.deleteRentalContractById(1L);

        verify(rentalContractRepository, times(1)).findById(1L);
        verify(rentalContractRepository, times(1)).deleteById(1L);
    }
}
