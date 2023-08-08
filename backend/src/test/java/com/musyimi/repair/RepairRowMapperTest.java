package com.musyimi.repair;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RepairRowMapperTest {

    @Test
    void mapRow() throws SQLException {

        RepairRowMapper repairRowMapper = new RepairRowMapper();
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("Kaku");
        when(resultSet.getString("title")).thenReturn("Nokia 3310");
        when(resultSet.getString("issue")).thenReturn("charging");
        when(resultSet.getString("brand")).thenReturn("Nokia");
        when(resultSet.getString("phone_number")).thenReturn("900000000");



        Repair actual = repairRowMapper.mapRow(resultSet, 1);

        Repair expected = new Repair(

                1,
                "Kaku",
                "Nokia 3310",
                "charging",
                "Nokia",
                "900000000"

        );

        assertThat(actual).isEqualTo(expected);
    }
}