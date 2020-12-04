package ui.controller;

import domain.db.DbException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Login extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        try{
            String userid = request.getParameter("userid").toLowerCase();
            String password = request.getParameter("password");

            Person person = service.get(userid);
            if (person != null && person.isCorrectPassword(password)){
                HttpSession session = request.getSession();
                session.setAttribute("login", person);
            }
            else{
                request.setAttribute("error", "No valid userid/password");
            }
        }catch (DbException e){
            request.setAttribute("error", "The given data is invalid");
        } catch (UnsupportedEncodingException e) {
            request.setAttribute("error", "unsupported encoding exception");
        } catch (NoSuchAlgorithmException e) {
            request.setAttribute("error", "No such algorithm");
        }
        return "Controller?command=Home";
    }

}
