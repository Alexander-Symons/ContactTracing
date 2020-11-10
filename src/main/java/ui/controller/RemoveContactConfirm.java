package ui.controller;

import domain.model.Contact;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class RemoveContactConfirm extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<>();
        List<Contact> contactlist= null;
        Person p = (Person) request.getSession().getAttribute("login");
        String chour = (String) request.getAttribute("hour");
        String cdate = (String) request.getAttribute("date");
        String cfname = (String) request.getAttribute("firstName");
        String clname = (String) request.getAttribute("lastName");

        if(p != null){
            if (p.getUserid().equals("admin")){
                contactlist = contactService.getAll();
            }
            else{
                contactlist = contactService.getAllFromUser(p.getUserid());
            }

            contactlist.removeIf(contact -> contact.getDateAsString().equals(cdate) && contact.getHour().equals(chour)
                    && contact.getFirstName().equals(cfname) && contact.getLastName().equals(clname));

        }
        return "Controller?command=Contacts";
    }

}
