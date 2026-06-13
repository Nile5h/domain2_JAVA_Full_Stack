package com.task.dao;

import javax.sql.DataSource;

import com.task.model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO {
    private final DataSource dataSource;

    public ContactDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addContact(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts(name, email, phone) VALUES(?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getEmail());
            stmt.setString(3, contact.getPhone());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Contact> getAllContacts() throws SQLException {
        return searchContacts(null);
    }

    @Override
    public List<Contact> searchContacts(String keyword) throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM contacts");
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
        
        if (hasKeyword) {
            sql.append(" WHERE LOWER(name) LIKE LOWER(?) OR LOWER(email) LIKE LOWER(?)");
        }
        sql.append(" ORDER BY created_at DESC");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            
            if (hasKeyword) {
                String pattern = "%" + keyword.trim() + "%";
                stmt.setString(1, pattern);
                stmt.setString(2, pattern);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contacts.add(mapRow(rs));
                }
            }
        }
        return contacts;
    }

    private Contact mapRow(ResultSet rs) throws SQLException {
        return new Contact(
            rs.getString("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("phone")
        );
    }
}
