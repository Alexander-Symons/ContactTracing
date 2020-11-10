package ui.controller;

import domain.db.PersonDBSQL;
import domain.service.ContactService;
import domain.service.PersonService;
import domain.service.TestResultService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PersonService personService = new PersonService();
	private final ContactService contactService = new ContactService();
	private final TestResultService testResultService = new TestResultService();
	private final HandlerFactory handlerFactory = new HandlerFactory();

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
		String destination = "index.jsp";
		String command = "home";
		if (request.getParameter("command") != null) {
			command = request.getParameter("command");
			try {
				RequestHandler handler = handlerFactory.getHandler(command, personService, contactService, testResultService);
				destination = handler.handleRequest(request, response);
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				destination = "index.jsp";
			}
		}
		request.getRequestDispatcher(destination).forward(request, response);
	}



}





