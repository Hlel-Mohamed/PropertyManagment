package de.tekup.propertymanagment.exception;

public class PropertyNotFoundException extends RuntimeException{
        public PropertyNotFoundException(Long id) {
            super("Property with ID " + id + " not found.");
        }
}
