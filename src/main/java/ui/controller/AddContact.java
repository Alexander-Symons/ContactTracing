package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddContact extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<>();

        Contact c = new Contact();

        setContactUserId(c, request, errors);
        setContactFirstName(c, request, errors);
        setContactLastName(c, request, errors);
        setContactEmail(c, request, errors);
        setContactGsm(c, request, errors);
        setContactDate(c, request, errors);
        setContactHour(c, request, errors);



        //errors.removeIf(Objects::isNull);
        if(errors.size() == 0){
            try{
                return "Controller?command=Contacts";


            }catch (DbException e){
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=Contacts";

    }

    private void setContactHour(Contact c, HttpServletRequest request, List<String> errors) {
        String hourstring = request.getParameter("hour").trim();
        try{
            c.setHour(LocalTime.parse(hourstring));
            request.setAttribute("hour", hourstring);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void setContactDate(Contact c, HttpServletRequest request, List<String> errors) {
        String datestring = request.getParameter("date").trim();
        try{
            c.setDate(LocalDate.parse(datestring));
            request.setAttribute("date", datestring);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }


    private void setContactGsm(Contact c, HttpServletRequest request, List<String> errors) {
        String gsm = request.getParameter("gsm").trim();
        try{
            c.setGsm(gsm);
            request.setAttribute("gsm", gsm);
        }catch (Exception e){
            errors.add(e.getMessage());
        }


    }


    private void setContactEmail(Contact c, HttpServletRequest request, List<String> errors) {
        String email = request.getParameter("email").trim();
        try{
            c.setEmail(email);
            request.setAttribute("email", email);
        }catch (Exception e){
            errors.add(e.getMessage());
        }

    }


    private void setContactLastName(Contact c, HttpServletRequest request, List<String> errors) {
        String lastname = request.getParameter("lastName");
        try{
            c.setLastName(lastname);
            request.setAttribute("lastName", lastname);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }


    private void setContactFirstName(Contact c, HttpServletRequest request, List<String> errors) {
        String firstname = request.getParameter("firstName");
        try{
            c.setFirstName(firstname);
            request.setAttribute("firstName", firstname);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }


    private void setContactUserId(Contact c, HttpServletRequest request, List<String> errors) {
        String userid = (String) request.getSession().getAttribute("login");
        try{
            c.setUserid(userid);
            System.out.println(errors);
            request.setAttribute("userid", userid);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }
}

