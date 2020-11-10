package ui.controller;

import domain.db.DbException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Add extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<>();

        Person p = new Person();
        setPersonUserId(p, request, errors);
        setPersonFirstName(p, request, errors);
        setPersonLastName(p, request, errors);
        setPersonEmail(p, request, errors);
        setPersonPassword(p, request, errors);


        errors.removeIf(Objects::isNull);
        if(errors.size() == 0){
            try{
                service.add(p);
                return "Controller?command=Overview";

            }catch (DbException e){
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=Register";

    }


    private void setPersonPassword(Person p, HttpServletRequest request, List<String> errors) {
        String password = request.getParameter("password").trim();
        try{
            p.setPassword(password);
            request.setAttribute("password", password);
        }catch (Exception e){
            errors.add(e.getMessage());
        }


    }


    private void setPersonEmail(Person p, HttpServletRequest request, List<String> errors) {
        String email = request.getParameter("email").trim();
        try{
            p.setEmail(email);
            request.setAttribute("email", email);
        }catch (Exception e){
            errors.add(e.getMessage());
        }

    }


    private void setPersonLastName(Person p, HttpServletRequest request, List<String> errors) {
        String lastname = request.getParameter("lastName");
        try{
            p.setLastName(lastname);
            request.setAttribute("lastName", lastname);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }


    private void setPersonFirstName(Person p, HttpServletRequest request, List<String> errors) {
        String firstname = request.getParameter("firstName");
        try{
            p.setFirstName(firstname);
            request.setAttribute("firstName", firstname);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }


        private void setPersonUserId(Person p, HttpServletRequest request, List<String> errors) {
            String userid = request.getParameter("userid");
            try{
                p.setUserid(userid);
                request.setAttribute("userid", userid);
            }catch (Exception e){
                errors.add(e.getMessage());
            }
        }
}
