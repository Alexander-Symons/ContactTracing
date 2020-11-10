package domain.service;

import domain.db.ContactDBSQL;
import domain.db.ContactDb;
import domain.model.Contact;
import domain.model.DomainException;
import domain.model.TestResult;

import java.util.List;

public class ContactService {

    private final ContactDb db = new ContactDBSQL();

    public ContactService() {
    }
    
    public List<Contact> getAllFromUser(String userid) {
        if (userid.isEmpty()) throw new DomainException("No user id given");
        return db.getAllFromUser(userid);
    }

    public List<Contact> getAll() {
        return db.getAll();
    }

    public void add(Contact contact) {
        if (contact == null) throw new DomainException("No contact given");

        db.add(contact);
    }

    public List<Contact> getAllFromUserAfterDate(TestResult testResult) {
        if (testResult == null) throw new DomainException("No test result given");
        return db.getAllFromUserAfterDate(testResult);
    }

}
