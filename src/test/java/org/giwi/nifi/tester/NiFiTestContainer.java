package org.giwi.nifi.tester;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;
import java.time.Duration;

public class NiFiTestContainer {

    private static final String NIFI_IMAGE = "apache/nifi:2.0.0-M2";
    private static GenericContainer<?> nifiContainer;

    public static synchronized GenericContainer<?> getInstance() {
        if (nifiContainer == null) {
            nifiContainer = new GenericContainer<>(DockerImageName.parse(NIFI_IMAGE))
                .withExposedPorts(8443)
                .withEnv("SINGLE_USER_CREDENTIALS_USERNAME", "admin")
                .withEnv("SINGLE_USER_CREDENTIALS_PASSWORD", "admin1234567")
                .waitingFor(Wait.forHttp("/nifi-api/access/config")
                    .forPort(8443)
                    .usingTls()
                    .allowInsecure()
                    .forStatusCode(200)
                    .withStartupTimeout(Duration.ofMinutes(5)));
            nifiContainer.start();
        }
        return nifiContainer;
    }

    public static String getApiUrl() {
        GenericContainer<?> container = getInstance();
        return "https://" + container.getHost() + ":" + container.getMappedPort(8443) + "/nifi-api";
    }

    public static String getUsername() {
        return "admin";
    }

    public static String getPassword() {
        return "admin1234567";
    }
}
