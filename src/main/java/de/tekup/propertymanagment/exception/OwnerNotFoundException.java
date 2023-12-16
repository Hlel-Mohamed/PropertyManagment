package de.tekup.propertymanagment.exception;

public class OwnerNotFoundException extends RuntimeException{
        public OwnerNotFoundException(Long id) {
            super("Owner with ID " + id + " not found.");
        }
}
