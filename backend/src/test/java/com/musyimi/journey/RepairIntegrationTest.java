package com.musyimi.journey;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.musyimi.repair.Repair;
import com.musyimi.repair.RepairRegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepairIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    private static final String REPAIR_URI = "/api/v1/repairs";


    @BeforeEach
    public void setUp() {
        webTestClient =
                webTestClient
                        .mutate()
                        .responseTimeout(Duration.ofMillis(30000))
                        .build();
    }


    @Test
    void canRegisterRepair() {

        Faker faker = new Faker();
        Name fakerName = faker.name();
        String name = fakerName.name();
        String title = fakerName.title();
        String brand = faker.company().name();
        String phoneNumber = faker.phoneNumber().cellPhone();
        String issue = fakerName.fullName();

        RepairRegistrationRequest request = new RepairRegistrationRequest(
                name, title, brand, phoneNumber, issue
        );


        webTestClient.post()
                .uri(REPAIR_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), RepairRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk();

        List<Repair> allRepairs = webTestClient.get()
                .uri(REPAIR_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Repair>() {
                })
               .returnResult()
               .getResponseBody();

        Repair expectedRepair = new Repair(
                name, title, brand, phoneNumber, issue
        );




//        int id = allRepairs.stream()
//                .filter(repair -> repair.getphoneNumber().equals(phoneNumber))
//                .map(Repair::getId)
//                .findFirst()
//                .orElseThrow();
//
//        expectedRepair.setId(id);
//
//        webTestClient.get()
//                .uri(REPAIR_URI + "/{id}" , id)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .expectBody(new ParameterizedTypeReference<Repair>() {
//                })
//                .isEqualTo(expectedRepair);
//
//        assertThat(allRepairs)
//                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
//                .contains(expectedRepair);
//


    }
}



