package com.musyimi.repair;

import com.musyimi.AbstractTestcontainersUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RepairJDBCDataAccessServiceTest extends AbstractTestcontainersUnitTest {

    private RepairJDBCDataAccessService underTest;
    private final RepairRowMapper repairRowMapper = new RepairRowMapper();

    @BeforeEach
    void setUp() {
        underTest = new RepairJDBCDataAccessService(
                getJdbcTemplate(),
                repairRowMapper
        );
    }

    @Test
    void selectAllRepairs() {
        Repair repair = new Repair(
                FAKER.name().fullName(),
                FAKER.name().title(),
                FAKER.company().name(),
                FAKER.book().publisher(),
                FAKER.phoneNumber().cellPhone()
        );
        underTest.insertRepair(repair);

        List<Repair> repairs = underTest.selectAllRepairs();

        assertThat(repairs).isNotEmpty();

    }

    @Test
    void selectRepairById() {
        int phoneNumber = FAKER.hashCode();

        Repair repair = new Repair(
                FAKER.name().fullName(),
                FAKER.name().title(),
                FAKER.company().name(),
                FAKER.book().publisher(),
                FAKER.phoneNumber().cellPhone()

        );

        underTest.insertRepair(repair);

        int id = underTest.selectAllRepairs()
                .stream()
                .filter(c -> c.getphoneNumber().equals(phoneNumber))
                .map(Repair::getId)
                .findFirst()
                .orElseThrow();

        Optional<Repair> actual = underTest.selectRepairById(id);

        assertThat(actual).isPresent().hasValueSatisfying(c ->{
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(repair.getName());
            assertThat(c.getBrand()).isEqualTo(repair.getBrand());
            assertThat(c.getTitle()).isEqualTo(repair.getTitle());
            assertThat(c.getIssue()).isEqualTo(repair.getIssue());
            assertThat(c.getphoneNumber()).isEqualTo(repair.getphoneNumber());
        });

    }

    @Test
    void willReturnEmptyWhenSelectRepairById() {
        int id = -1;

        var actual = underTest.selectRepairById(id);

        assertThat(actual).isEmpty();
    }

    @Test
    void insertRepair() {
    }

    @Test
    void existsPersonWithPhoneNumber() {
    }

    @Test
    void existsRepairWithId() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateRepair() {
    }
}