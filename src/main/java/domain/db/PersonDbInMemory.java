package domain.db;

import domain.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDbInMemory {

    Map<String, Person> persondb = new HashMap<String, Person>();


    public Person get(String personId) {
        return persondb.get(personId);
    }

    public List getAll() {
        List values = new ArrayList();
        values.addAll(persondb.values());
        return values;
    }

    public void add(Person person) {
        persondb.put(person.getUserid(), person);
    }

    public void update(Person person) {
        persondb.put(person.getUserid(), person);
    }

    public void delete(String id) {
        persondb.remove(id);
    }
}
