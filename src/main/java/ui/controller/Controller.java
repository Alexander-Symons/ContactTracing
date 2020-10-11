package ui.controller;

import domain.db.DbException;
import domain.db.PersonDbInMemory;
import domain.db.PersonService;
import domain.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonDbInMemory db = new PersonDbInMemory();

    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination;
		String command = "home";
		if (request.getParameter("command") != null) {
			command = request.getParameter("command");
		}

		switch(command) {
			case "home" :
				destination = home(request, response);
				break;
			case "overview":
				destination = overview(request, response);
				break;
			case "register":
				destination = register(request, response);
				break;
			case "add":
				destination = add(request, response);
				break;
			case "login":
				destination = login(request, response);
				break;
			case "logout":
				destination = logout(request, response);
				break;
			case "changepsswd":
				destination = changepassword(request, response);
				break;
			default :
				destination = home(request, response);
		}
		request.getRequestDispatcher(destination).forward(request, response);
	}

	private String changepassword(HttpServletRequest request, HttpServletResponse response) {
    	try {
			String newpassword = request.getParameter("newpassword");
			HttpSession session = request.getSession();
			Person p = (Person) session.getAttribute("login");
			if(newpassword != null){
				db.get(p.getUserid()).setPassword(newpassword);
			}


		}catch (Exception e){
			request.setAttribute("errorschangepsswd", "The given new password is invalid");
		}
    	return "Controller?command=home";
	}


	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "Controller?command=home";
	}


	private String login(HttpServletRequest request, HttpServletResponse response) {
    	try{
			String userid = request.getParameter("userid").toLowerCase();
			String password = request.getParameter("password");

			Person person = db.get(userid);
			if (person != null && person.isCorrectPassword(password)){
				HttpSession session = request.getSession();
				session.setAttribute("login", person);
			}
			else{
				request.setAttribute("error", "No valid userid/password");
			}
		}catch (DbException e){
			request.setAttribute("error", "The given data is invalid");
		}
		return "Controller?command=home";
	}


	private String home(HttpServletRequest request, HttpServletResponse response) {
		return "index.jsp";
	}


	private String overview(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("users", db.getAll());
		return "personoverview.jsp";
	}


	private String register(HttpServletRequest request, HttpServletResponse response) {
		return "register.jsp";
	}


	private String add(HttpServletRequest request, HttpServletResponse response) {
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
				if(db.get(p.getUserid()) != null){
					errors.add("User already exists");
				}
				else{
					db.add(p);
					return home(request, response);
				}

			}catch (DbException e){
				errors.add(e.getMessage());
			}
		}
		request.setAttribute("errors", errors);
		return "register.jsp";

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





