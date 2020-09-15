package pl.arcadeit.forex.configuration;

import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayConfigurationCustomizer;
import org.springframework.stereotype.Component;

/*
    Configuration class. Allows to add migrations older than schema version (baselineOnMigrate)
    and add migrations with changed order (outOfOrder)
 */

@Component
public class FlyWayConfig implements FlywayConfigurationCustomizer {

    @Override
    public void customize(final FluentConfiguration configuration) {
        configuration.baselineOnMigrate(false).outOfOrder(true);
    }
}
