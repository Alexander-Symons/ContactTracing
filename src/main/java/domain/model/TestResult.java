package domain.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestResult {
    private String userid;
    private LocalDate date;

    public TestResult() {}

    public TestResult(String userId, LocalDate date) {
        setUserId(userId);
        setDate(date);
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userid) {
        if (userid.isEmpty()) throw new DomainException("User id can't be empty");
        this.userid = userid;
    }

    public String getDateAsString() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null) throw new DomainException("Date can't be empty");
        if ((date.isAfter(LocalDate.now()))) throw new DomainException("Date has to be before today");
        this.date = date;
    }
}
