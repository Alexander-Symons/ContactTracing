package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.Person;
import domain.model.TestResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Testresult extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Exception> errors = null;
        List<TestResult> testresultlist = null;
        Person p = (Person) request.getSession().getAttribute("login");

        if(p != null){
            if (p.getRole().equals("admin")){
                testresultlist = testResultService.getAll();
            }
            try{
                request.setAttribute("testresults", testresultlist);
            }catch (Exception e){
                errors.add(e);
                request.setAttribute("errors", errors);
            }

        }

        return "registertestresult.jsp";

    }
}
