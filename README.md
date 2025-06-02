package com.yourapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TableValidationService {

    private static final Set<String> ALLOWED_TABLES = new HashSet<>(Arrays.asList(
        "FR_T40", "FR_T50", "FR_T60" // Add your known allowed table names here
    ));

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TableValidationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Validates whether the given table name is allowed and exists in the Oracle database schema.
     *
     * @param tableName table name from user input
     * @return true if valid, false otherwise
     */
    public boolean isValidTableName(String tableName) {
        if (tableName == null || tableName.trim().isEmpty()) {
            return false;
        }

        String upperTable = tableName.toUpperCase();

        // Step 1: Optional whitelist check
        if (!ALLOWED_TABLES.contains(upperTable)) {
            return false;
        }

        // Step 2: Check with database metadata
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            String schema = conn.getSchema();

            try (ResultSet rs = metaData.getTables(null, schema, upperTable, new String[]{"TABLE"})) {
                return rs.next();
            }

        } catch (SQLException e) {
            // Log it
            System.err.println("Error checking table metadata: " + e.getMessage());
            return false;
        }
    }

    /**
     * Returns the list of all available table names in current schema (Optional utility).
     */
    public List<String> getAllTableNames() {
        List<String> tableNames = new ArrayList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            String schema = conn.getSchema();

            try (ResultSet rs = metaData.getTables(null, schema, "%", new String[]{"TABLE"})) {
                while (rs.next()) {
                    tableNames.add(rs.getString("TABLE_NAME"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving tables: " + e.getMessage());
        }

        return tableNames.stream().sorted().collect(Collectors.toList());
    }
}
