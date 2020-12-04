package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ChangeRoleConfirm extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        String role = request.getParameter("role");
        List<Person> users = service.getAll();
        List<Exception> errors = new ArrayList<>();
        System.out.println(userid);
        System.out.println(role);

        for (Person user: users) {
            if(user.getUserid().equals(userid)){
                user.setRole(role);
                service.update(user);
                request.setAttribute("users", service.getAll());
                return "personoverview.jsp";
            }
        }

        errors.add(new IllegalArgumentException("this is not a valid user"));
        request.setAttribute("errors", errors);
        request.setAttribute("users", service.getAll());
        return "personoverview.jsp";
    }
}
