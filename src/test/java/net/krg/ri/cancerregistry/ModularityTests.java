package net.krg.ri.cancerregistry;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModularityTests {

    @Test
    void verifyModularity() {
        ApplicationModules.of(CancerRegistryApplication.class).verify();
    }
}
