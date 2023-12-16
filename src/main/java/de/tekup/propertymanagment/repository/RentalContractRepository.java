package de.tekup.propertymanagment.repository;

import de.tekup.propertymanagment.entity.RentalContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalContractRepository extends JpaRepository<RentalContract, Long> {
    List<RentalContract> findAllByPropertyId(Long id);
    List<RentalContract> findAllByOwnerId(Long id);
}
