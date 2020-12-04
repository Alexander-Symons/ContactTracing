package ui.controller;


import domain.model.Contact;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
public class Contacts extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Contact> contactslist = null;
        Person p = (Person) request.getSession().getAttribute("login");

        if(p != null){
            if (p.getRole().equals("admin")){
                contactslist = contactService.getAll();
            }
            else{
                contactslist = contactService.getAllFromUser(p.getUserid());
            }

            request.setAttribute("contacts", contactslist);
        }
        return "contacts.jsp";
    }
}
