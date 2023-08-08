package com.musyimi.repair;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jdbc")
public class RepairJDBCDataAccessService implements RepairDao{

    private final JdbcTemplate jdbcTemplate;
    private final RepairRowMapper repairRowMapper;

    public RepairJDBCDataAccessService(JdbcTemplate jdbcTemplate, RepairRowMapper repairRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.repairRowMapper = repairRowMapper;
    }


    @Override
    public List<Repair> selectAllRepairs() {
        var sql = """
                SELECT id, name, title, issue, brand, phone_number
                FROM repair
                """;

        return jdbcTemplate.query(sql, repairRowMapper);

    }

    @Override
    public Optional<Repair> selectRepairById(Integer id) {
        var sql = """
                SELECT id, name, title, issue, brand, phone_number
                FROM repair
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql, repairRowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public void insertRepair(Repair repair) {
         var sql = """
                 INSERT INTO repair(name, title, brand, issue, phone_number)
                 VALUES (?, ?, ?, ?, ?)
                 """;
         jdbcTemplate.update(
                 sql,
                 repair.getBrand(),
                 repair.getIssue(),
                 repair.getTitle(),
                 repair.getName(),
                 repair.getphoneNumber()
         );
    }

    @Override
    public boolean existsPersonWithPhoneNumber(String phoneNumber) {
        var sql = """
                SELECT count(id)
                FROM repair
                WHERE phone_number = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, phoneNumber);
        return count != null && count > 0;
    }

    @Override
    public boolean existsRepairWithId(Integer id) {
        var sql = """
                SELECT count(id)
                FROM repair
                WHERE id = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public void deleteById(Integer id) {
        var sql = """
                DELETE
                FROM repair
                WHERE id = ?
                """;
        int result = jdbcTemplate.update(sql, id);

    }

    @Override
    public void updateRepair(Repair update) {
        if (update.getName() != null) {
            String sql = "UPDATE repair SET name = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getName(),
                    update.getId()
            );
        }

        if (update.getBrand() != null) {
            String sql = "UPDATE repair SET brand = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getBrand(),
                    update.getId()
            );
        }

        if (update.getTitle() != null) {
            String sql = "UPDATE repair SET title = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getTitle(),
                    update.getId()
            );
        }

        if (update.getIssue() != null) {
            String sql = "UPDATE repair SET issue = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getIssue(),
                    update.getId()
            );
        }

        if (update.getphoneNumber() != null) {
            String sql = "UPDATE repair SET phone_number = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getphoneNumber(),
                    update.getId()
            );
        }


    }
}
