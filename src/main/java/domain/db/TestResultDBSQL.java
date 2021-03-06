package domain.db;

import domain.model.TestResult;
import util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestResultDBSQL implements TestResultDb {
    private final Connection connection;
    private final String schema;

    public TestResultDBSQL() {
        connection = DbConnectionService.getDbConnection();
        schema = DbConnectionService.getSchema();
        System.out.println("Schema: " + schema);
    }

    @Override
    public void add(TestResult testResult) {

        String sql = String.format("INSERT INTO %s.testresult(userid, date) VALUES (?, ?);", schema);
        //ON CONFLICT (userid) DO UPDATE SET date = excluded.date
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, testResult.getUserId());
            statementSql.setString(2, testResult.getDate().toString());
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public boolean isUserPositive(String userId) {

        String sql = String.format("SELECT * FROM %s.testresult WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userId);
            ResultSet result = statementSql.executeQuery();

            return result.next();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public List<TestResult> getAll() {

        List<TestResult> testResults = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.testresult ORDER BY date", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {

                TestResult testResult = makeTestResult(result);
                testResults.add(testResult);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return testResults;
    }

    @Override
    public TestResult getTestResultFromUser(String userId) {

        String sql = String.format("SELECT * FROM %s.testresult WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userId);
            ResultSet result = statementSql.executeQuery();

            if (result.next()) return makeTestResult(result);
            else return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    private TestResult makeTestResult(ResultSet result) throws SQLException {

        String userId = result.getString("userid");
        String dateString = result.getString("date");
        LocalDate date = LocalDate.parse(dateString);

        return new TestResult(userId, date);
    }
}