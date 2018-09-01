package nz.co.rajees.springboot.features;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

/**
 * Use Togglz to enable features in this application
 */
public enum AppFeature implements Feature {

    @Label("Activates the secret feature")
    @EnabledByDefault
    HOME_FEATURE;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
