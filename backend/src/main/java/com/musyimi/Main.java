package com.musyimi;

import com.github.javafaker.Faker;
import com.musyimi.repair.Repair;
import com.musyimi.repair.RepairRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        
    }

    @Bean
    CommandLineRunner runner(RepairRepository repairRepository) {

        return args -> {
            var faker = new Faker();
            Repair repair = new Repair(
                    faker.name().fullName(),
                    faker.name().title(),
                    faker.company().name(),
                    faker.book().publisher(),
                    faker.phoneNumber().cellPhone()
            );

            repairRepository.save(repair);

        };
    }
}
