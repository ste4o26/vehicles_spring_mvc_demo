package spring.demos.car_system.init;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

public class ErrorMessage {
    public final static Supplier<EntityNotFoundException> entityNotFoundException(String message, String className, String argument) {
        return () -> new EntityNotFoundException(String.format(message, className, argument));
    }

    public static final String NOT_EXISTING_ENTITY_WITH_NAME = "No %s with name %s has been found!";

    public static final String NOT_EXISTING_ENTITY_WITH_ID = "No %s with ID %s has been found!";

    public static final String ENTITY_WITH_NAME_ALREADY_EXISTS = "%s %s already exists!";

    public static final String USERNAME_CANNOT_BE_CHANGED = "Username can not be changed!";

    public static final String NOT_EXISTING_OFFER_WITH_CATEGORY = "No offers have been found for %s %s!";
}
