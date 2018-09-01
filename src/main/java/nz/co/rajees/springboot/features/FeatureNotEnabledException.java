package nz.co.rajees.springboot.features;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Throw this exception if a feature is not available for this user.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class FeatureNotEnabledException extends RuntimeException {
}
