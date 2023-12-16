package de.tekup.propertymanagment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RentalContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tenant;
    private double monthlyRent;
    @ManyToOne
    private Owner owner;
    @ManyToOne
    private Property property;
}
