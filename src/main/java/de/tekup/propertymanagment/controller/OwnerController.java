package de.tekup.propertymanagment.controller;

import de.tekup.propertymanagment.entity.Owner;
import de.tekup.propertymanagment.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
@AllArgsConstructor
public class OwnerController {
    private OwnerService ownerService;

    @PostMapping("/create")
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner){
        return new ResponseEntity<>(ownerService.saveOwner(owner), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id){
        return new ResponseEntity<>(ownerService.findOwnerById(id), HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Owner>> getAllOwners(){
        return new ResponseEntity<>(ownerService.findAllOwners(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner newOwner){
        return new ResponseEntity<>(ownerService.updateOwner(id, newOwner), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable Long id){
        ownerService.deleteOwnerById(id);
        return new ResponseEntity<>("Owner N°"+ id +" deleted successfully", HttpStatus.OK);
    }
}
