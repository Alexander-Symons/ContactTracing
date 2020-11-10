package domain.service;

import domain.db.TestResultDBSQL;
import domain.model.DomainException;
import domain.model.TestResult;

import java.util.List;

public class TestResultService {

    private final TestResultDBSQL db = new TestResultDBSQL();

    public TestResultService() {}

    public boolean isUserPositive(String userid) {
        if (userid.isEmpty()) throw new DomainException("No user id given");
        return db.isUserPositive(userid);
    }

    public TestResult getTestResultFromUser(String userid) {
        if (userid.isEmpty()) throw new DomainException("No user id given");
        return db.getTestResultFromUser(userid);
    }

    public List<TestResult> getAll() {
        return db.getAll();
    }

    public void add(TestResult testResult) {
        // Exception if test result is null
        if (testResult == null) throw new DomainException("No testResult given");

        // Add test result
        db.add(testResult);
    }

}
