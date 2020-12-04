package ui.controller;

import domain.model.Contact;
import domain.model.Person;
import domain.model.TestResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Search extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        Person p = (Person)request.getSession().getAttribute("login");
        TestResult t = testResultService.getTestResultFromUser(p.getUserid());
        List<Contact> contacts = contactService.getAllFromUser(p.getUserid());
        List<Contact> possiblepositives = new ArrayList<Contact>();
        for (Contact contact: contacts) {
            if (contact.getDate().isAfter(t.getDate())){
                possiblepositives.add(contact);
            }
        }
        request.setAttribute("contacts", possiblepositives);
        return "search.jsp";
    }
}
