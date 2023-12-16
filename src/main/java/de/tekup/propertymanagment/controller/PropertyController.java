package de.tekup.propertymanagment.controller;

import de.tekup.propertymanagment.entity.Property;
import de.tekup.propertymanagment.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property")
@AllArgsConstructor
public class PropertyController {
    private PropertyService propertyService;

    @PostMapping("/create")
    public ResponseEntity<Property> createProperty(@RequestBody Property property){
        return new ResponseEntity<>(propertyService.saveProperty(property), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id){
        return new ResponseEntity<>(propertyService.findPropertyById(id), HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Property>> getAllProperties(){
        return new ResponseEntity<>(propertyService.findAllProperties(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property newProperty){
        return new ResponseEntity<>(propertyService.updateProperty(id, newProperty), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id){
        propertyService.deletePropertyById(id);
        return new ResponseEntity<>("Property N°"+ id +" deleted successfully", HttpStatus.OK);
    }
}
