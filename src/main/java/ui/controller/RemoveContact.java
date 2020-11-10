package ui.controller;

import domain.model.Contact;
import domain.model.DomainException;
import domain.service.ContactService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class RemoveContact extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        List<String> errors = new ArrayList<>();
        List<Contact> contactList= contactService.getAll();
        if(!contactList.isEmpty()){
            return "removecontact.jsp";
        }
        else{
            errors.add("The contactlist is empty");
        }
        request.setAttribute("errors", errors);
        return "Controller?command=Contacts";
    }
}
