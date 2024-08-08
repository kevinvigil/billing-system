package com.system.billingsystem.integrations;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Collections;

import static domain.tables.Company.COMPANY;
import static domain.tables.Invoice.INVOICE;
import static domain.tables.Product.PRODUCT;
import static domain.tables.InvoiceProduct.INVOICE_PRODUCT;
import static domain.tables.Customer.CUSTOMER;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(
        initializers = {BaseIntegrationTest.Initializer.class},
        classes = {BaseIntegrationTest.TestConfig.class}
)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class BaseIntegrationTest {

    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    protected DSLContext dslContext;


    @BeforeEach
    protected void beforeEach() {
        dslContext.truncateTable(COMPANY).cascade().execute();
        dslContext.truncateTable(INVOICE).cascade().execute();
        dslContext.truncateTable(PRODUCT).cascade().execute();
        dslContext.truncateTable(INVOICE_PRODUCT).cascade().execute();
        dslContext.truncateTable(CUSTOMER).cascade().execute();
    }

    private static final WireMockServer wiremockServer;
    private static final PostgreSQLContainer<?> postgreSQLContainer;

    static {
        wiremockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wiremockServer.start();

        postgreSQLContainer = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("billing_system")
                .withUsername("postgres")
                .withPassword("postgres")
                .withReuse(true);

        // Start the containers
        Startables.deepStart(Collections.singletonList(postgreSQLContainer)).join();

        // Add shutdown hooks to stop the containers
        Runtime.getRuntime().addShutdownHook(new Thread(() -> postgreSQLContainer.stop()));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> wiremockServer.stop()));
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "DB_ADDRESS=" + postgreSQLContainer.getJdbcUrl(),
                    "DB_USER=" + postgreSQLContainer.getUsername(),
                    "DB_PASS=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @TestConfiguration
    public static class TestConfig {

    }
}
