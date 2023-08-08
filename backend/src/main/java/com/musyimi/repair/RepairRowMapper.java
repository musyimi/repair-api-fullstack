package com.musyimi.repair;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RepairRowMapper implements RowMapper<Repair> {
    @Override
    public Repair mapRow(ResultSet rs, int rowNum) throws SQLException {
        Repair repair = new Repair(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("title"),
                rs.getString("issue"),
                rs.getString("brand"),
                rs.getString("phone_number")
        );
        return repair;
    }
}
