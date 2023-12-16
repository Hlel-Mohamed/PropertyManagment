package de.tekup.propertymanagment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contactInformation;
}
