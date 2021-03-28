package com.celil.humanresourcesqueryscreen;

import com.celil.humanresourcesqueryscreen.HumanResourcesQueryScreenApp;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = HumanResourcesQueryScreenApp.class)
public @interface IntegrationTest {
}
