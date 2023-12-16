package de.tekup.propertymanagment.exception;

public class RentalContractNotFoundException extends RuntimeException{
        public RentalContractNotFoundException(Long id) {
            super("RentalContract with ID " + id + " not found.");
        }
}
