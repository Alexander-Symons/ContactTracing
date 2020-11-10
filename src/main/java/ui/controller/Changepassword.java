package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Changepassword extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            String newpassword = request.getParameter("newpassword");
            HttpSession session = request.getSession();
            Person p = (Person) session.getAttribute("login");
            if(newpassword != null){
                service.get(p.getUserid()).setPassword(newpassword);
            }


        }catch (Exception e){
            request.setAttribute("errorschangepsswd", "The given new password is invalid");
        }
        return "Controller?command=Home";
    }
}
