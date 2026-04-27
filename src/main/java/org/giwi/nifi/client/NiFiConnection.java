package org.giwi.nifi.client;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NiFiConnection {
    String url() default "https://localhost:8443/nifi-api";
    String user() default "admin";
    String password() default "admin";
}
