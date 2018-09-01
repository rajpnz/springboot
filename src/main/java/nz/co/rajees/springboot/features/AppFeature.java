package nz.co.rajees.springboot.features;

import org.togglz.core.Feature;
import org.togglz.core.annotation.ActivationParameter;
import org.togglz.core.annotation.DefaultActivationStrategy;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;
import org.togglz.spring.activation.SpringEnvironmentPropertyActivationStrategy;

/**
 * Use Togglz to enable features in this application
 *
 * @see <a href="https://www.baeldung.com/spring-togglz"></a>
 * @see <a href="https://medium.com/@bogdandraghicescu/spring-boot-feature-toggles-fe66cfa7504d"></a>
 */
public enum AppFeature implements Feature {

    @Label("Activates the home feature")
    @DefaultActivationStrategy(id= SpringEnvironmentPropertyActivationStrategy.ID,
        parameters = {@ActivationParameter(name = "name", value="home_feature")})
    @EnabledByDefault
    HOME_FEATURE;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
