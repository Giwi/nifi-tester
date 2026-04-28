package org.giwi.nifi.client;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

class NiFiConnectionExtension implements BeforeEachCallback {

    private static final String NIFI_URL = "nifi.url";
    private static final String NIFI_USER = "nifi.user";
    private static final String NIFI_PASS = "nifi.pass";

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        String url = System.getProperty(NIFI_URL, "https://localhost:8443/nifi-api");
        String user = System.getProperty(NIFI_USER, "admin");
        String password = System.getProperty(NIFI_PASS, "admin");

        AnnotatedElement element = context.getElement().get();

        if (element instanceof Class<?> clazz) {
            NiFiConnection annotation = clazz.getAnnotation(NiFiConnection.class);
            if (annotation != null) {
                url = annotation.url();
                user = annotation.user();
                password = annotation.password();
            }
        } else if (element instanceof Method method) {
            NiFiConnection annotation = method.getAnnotation(NiFiConnection.class);
            if (annotation != null) {
                url = annotation.url();
                user = annotation.user();
                password = annotation.password();
            }
        }

        System.setProperty(NIFI_URL, url);
        System.setProperty(NIFI_USER, user);
        System.setProperty(NIFI_PASS, password);
    }
}
