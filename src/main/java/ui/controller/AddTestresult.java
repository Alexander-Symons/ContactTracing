package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.Person;
import domain.model.TestResult;
import domain.service.TestResultService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddTestresult extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<>();

        TestResult t = new TestResult();

        setTestresultUserId(t, request, errors);
        setTestresultDate(t, request, errors);



        errors.removeIf(Objects::isNull);
        if(errors.size() == 0){
            try{
                testResultService.add(t);
                return "Controller?command=Contacts";


            }catch (DbException e){
                errors.add(e.getMessage());
            }
        }

        request.setAttribute("errors", errors);
        return "Controller?command=Contacts";

    }


    private void setTestresultDate(TestResult t, HttpServletRequest request, List<String> errors) {
        String datestring = request.getParameter("date").trim();
        try{
            t.setDate(LocalDate.parse(datestring));
            request.setAttribute("date", datestring);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void setTestresultUserId(TestResult t, HttpServletRequest request, List<String> errors) {
        Person p = (Person) request.getSession().getAttribute("login");
        if(p == null){
            errors.add("The user is not logged in");
        }
        String userid = p.getUserid();
        try{
            t.setUserId(userid);
            request.setAttribute("userid", userid);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }
}
