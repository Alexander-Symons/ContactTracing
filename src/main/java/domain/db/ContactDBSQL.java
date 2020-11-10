package domain.db;

import domain.model.Contact;
import domain.model.TestResult;
import util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ContactDBSQL implements ContactDb{

    private final Connection connection;
    private final String schema;

    public ContactDBSQL() {
        connection = DbConnectionService.getDbConnection();
        schema = DbConnectionService.getSchema();
        System.out.println("Schema: " + schema);
    }

    @Override
    public void add(Contact contact) {
        String sql = String.format("INSERT INTO %s.contact(userid, firstname, lastname, date, hour, gsm, email) VALUES (?, ?, ?, ?, ?, ?, ?);", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, contact.getUserid());
            statementSql.setString(2, contact.getFirstName());
            statementSql.setString(3, contact.getLastName());
            statementSql.setString(4, contact.getDate().toString());
            statementSql.setString(5, contact.getHour().toString());
            statementSql.setString(6, contact.getGsm());
            statementSql.setString(7, contact.getEmail());
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Contact> getAllFromUser(String userId) {
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact WHERE userid = ? ORDER BY lastname, firstname, date, hour", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userId);
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {

                Contact contact = createContact(result);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return contacts;
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact ORDER BY userid, lastname, firstname, date, hour", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {

                Contact contact = createContact(result);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return contacts;
    }

    @Override
    public List<Contact> getAllFromUserAfterDate(TestResult testResult) {
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact WHERE userid = ?" +
                "AND TO_DATE(date, 'YYYY-MM-DD') >= TO_DATE(?,'YYYY-MM-DD') " +
                "ORDER BY lastname, firstname, date, hour", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, testResult.getUserId());
            statementSql.setString(2, testResult.getDate().toString());
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {

                Contact contact = createContact(result);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return contacts;
    }

    public Contact createContact(ResultSet resultset) throws SQLException {
        String userId = resultset.getString("userid");
        String firstName = resultset.getString("firstname");
        String lastName = resultset.getString("lastname");
        String dateString = resultset.getString("date");
        LocalDate date = LocalDate.parse(dateString);
        String hourString = resultset.getString("hour");
        LocalTime hour = LocalTime.parse(hourString);
        String gsm = resultset.getString("gsm");
        String email = resultset.getString("email");

        return new Contact(userId, firstName, lastName, date, hour, gsm, email);
    }
}
