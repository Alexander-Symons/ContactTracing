package domain.service;

import domain.db.DbException;
import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.model.Person;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonService {
	private PersonDB db = new PersonDBSQL();
	
	public PersonService () {

		Person administrator = null;
		try {
			//t = 99f97d455d5d62b24f3a942a1abc3fa8863fc0ce2037f52f09bd785b22b800d4f2e7b2b614cb600ffc2a4fe24679845b24886d69bb776fcfa46e54d188889c6f
			administrator = new Person("admin", "admin@ucll.be", "99f97d455d5d62b24f3a942a1abc3fa8863fc0ce2037f52f09bd785b22b800d4f2e7b2b614cb600ffc2a4fe24679845b24886d69bb776fcfa46e54d188889c6f", "Ad", "Ministrator", "admin");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		add(administrator);
	}
	
	public Person get(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		return db.get(personId);
	}
	
	public List<Person> getAll(){
		return db.getAll();
	}

	public void add(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if (!db.isPersonInDb(person.getUserid())) {
			db.add(person);
		}

	}
	
	public void update(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if(!db.isPersonInDb(person.getUserid())){
			throw new DbException("No person found");
		}
		db.remove(person.getUserid());
		db.add(person);
	}
	
	public void delete(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		db.remove(personId);
	}

	public int getNumberOfPersons() {
		return db.getAll().size();
	}
}
