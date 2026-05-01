package org.giwi.nifi.tester;

import java.lang.annotation.*;

/**
 * Annotation for configuring NiFi connection parameters in JUnit tests.
 *
 * <p>This annotation can be applied to test classes or methods to specify
 * the NiFi instance connection details. When used with {@link NiFiConnectionExtension},
 * it automatically configures the {@link PipelineTester} with the specified
 * connection parameters.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * &#064;NiFiConnection(url = "https://nifi.example.com:8443/nifi-api",
 *                  user = "testuser",
 *                  password = "testpass")
 * public class MyPipelineTest {
 *     // tests here
 * }
 * </pre>
 *
 * @author GiWi
 * @see NiFiConnectionExtension
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NiFiConnection {
    /**
     * The NiFi API URL to connect to.
     *
     * @return The NiFi API base URL
     */
    String url() default "https://localhost:8443/nifi-api";

    /**
     * The username for NiFi authentication.
     *
     * @return The username
     */
    String user() default "admin";

    /**
     * The password for NiFi authentication.
     *
     * @return The password
     */
    String password() default "admin";
}
