package com.cgm.test;

import com.cgm.test.model.Contact;
import com.cgm.test.repository.ContactRepository;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {ContactServiceTest.Initializer.class})
public class ContactServiceTest {

    @Autowired
    ContactRepository contactRepository;

    @ClassRule
    public static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:15.0")
            .withDatabaseName("test")
            .withUsername("postgres")
            .withPassword("changeme");

    @BeforeAll
    public static void setUp() {
        postgresqlContainer.withReuse(true);
        postgresqlContainer.withInitScript("scripts/create_table.sql");
        postgresqlContainer.start();
    }

    @AfterAll
    public static void tearDown() {
        postgresqlContainer.stop();
    }

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", postgresqlContainer::getDriverClassName);
    }

    @Test
    public void test_get_contacts_by_name_and_tenant_id() {
        var contact1 = Contact.builder().matrixId("1").name("Samantha")
                .tenantId("1")
                .build();

        var contact2 = Contact.builder().matrixId("2").name("Samantha")
                .tenantId("1")
                .build();

        var contact3 = Contact.builder().matrixId("3").name("Samantha")
                .tenantId("2")
                .build();

        var contact4 = Contact.builder().matrixId("4").name("Ethan")
                .tenantId("1")
                .build();

        var contacts = List.of(contact1, contact2, contact3, contact4);

        contactRepository.saveAll(contacts);

        var result = contactRepository.findAllByTenantIdAndName("1", "Samantha");
        var expected = List.of(contact1, contact2);
        assertEquals(expected, result);

    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgresqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgresqlContainer.getUsername(),
                    "spring.datasource.password=" + postgresqlContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
