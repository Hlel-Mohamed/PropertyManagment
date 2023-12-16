package de.tekup.propertymanagment.controller;

import de.tekup.propertymanagment.entity.RentalContract;
import de.tekup.propertymanagment.service.RentalContractService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contract")
@AllArgsConstructor
public class RentalContractController {
    private RentalContractService rentalContractService;

    @PostMapping("/create")
    public ResponseEntity<RentalContract> createContract(@RequestBody RentalContract rentalContract){
        return new ResponseEntity<>(rentalContractService.saveRentalContract(rentalContract), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RentalContract> getContractById(@PathVariable Long id){
        return new ResponseEntity<>(rentalContractService.findRentalContractById(id), HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RentalContract>> getAllContracts(){
        return new ResponseEntity<>(rentalContractService.findAllRentalContracts(), HttpStatus.OK);
    }

    @GetMapping("/all/property/{id}")
    public ResponseEntity<List<RentalContract>> getAllContractsByPropertyId(@PathVariable Long id){
        return new ResponseEntity<>(rentalContractService.findAllRentalContractsByPropertyId(id), HttpStatus.OK);
    }

    @GetMapping("/all/owner/{id}")
    public ResponseEntity<List<RentalContract>> getAllContractsByOwnerId(@PathVariable Long id){
        return new ResponseEntity<>(rentalContractService.findAllRentalContractsByOwnerId(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RentalContract> updateContract(@PathVariable Long id, @RequestBody RentalContract newRentalContract){
        return new ResponseEntity<>(rentalContractService.updateRentalContract(id, newRentalContract), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContract(@PathVariable Long id){
        rentalContractService.deleteRentalContractById(id);
        return new ResponseEntity<>("Contract N°"+ id +" deleted successfully", HttpStatus.OK);
    }

}
