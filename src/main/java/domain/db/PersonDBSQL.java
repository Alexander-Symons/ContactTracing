package domain.db;

import domain.model.Person;
import util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDBSQL implements PersonDB{
    private final Connection connection;
    private final String schema;

    public PersonDBSQL(){
        connection = DbConnectionService.getDbConnection();
        schema = DbConnectionService.getSchema();
        System.out.println("Schema: " + schema);
    }


    @Override
    public void add(Person person) {
        String sql = String.format("INSERT INTO %s.person(userid, email, password, firstname, lastname) VALUES (?, ?, ?, ?, ?)", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, person.getUserid());
            statementSql.setString(2, person.getEmail());
            statementSql.setString(3, person.getPassword());
            statementSql.setString(4, person.getFirstName());
            statementSql.setString(5, person.getLastName());
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    public List getAll() {
        List<Person> personlist = new ArrayList();
        String sql = String.format("SELECT * FROM %s.person WHERE userid != 'admin'", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {

                Person person = createPerson(result);
                personlist.add(person);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return personlist;
    }

    @Override
    public void remove(String personId) {
        String sql = String.format("DELETE FROM %s.person WHERE userid = ?", schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, personId);
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public boolean isPersonInDb(String personId) {
        String sql = String.format("SELECT * FROM %s.person WHERE userid = ?", schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, personId);
            ResultSet result = statementSql.executeQuery();

            return result.next();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public Person get(String personId) {

        String sql = String.format("SELECT * FROM %s.person WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, personId);
            ResultSet result = statementSql.executeQuery();
            result.next();

            return createPerson(result);
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public Person createPerson(ResultSet resultset) throws SQLException {
        String userid = resultset.getString("userid");
        String email = resultset.getString("email");
        String password = resultset.getString("password");
        String firstName = resultset.getString("firstname");
        String lastName = resultset.getString("lastname");

        return new Person(userid, email, password, firstName, lastName);
    }
}
