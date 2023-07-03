package com.met.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.met.model.Employee;
import com.met.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired(required = true)
	private EmployeeService employeeService;
	
	   @GetMapping("/home")
	    public String showHomePage() {
	        return "home";
	    }

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "register";
	}

	@PostMapping("/register")
	public String processRegistrationForm(@ModelAttribute("employee") Employee employee) {
		// Save the employee to the database using Hibernate via service class
		employeeService.registerEmployee(employee);

		return "redirect:/mvc/employee/login";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "login";
	}

	@PostMapping("/login")
	public String processLoginForm(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
		// Validate the employee's credentials and perform the login
		boolean loggedIn = employeeService.loginUser(employee.getId(), employee.getPassword());
		if (loggedIn) {
			 redirectAttributes.addAttribute("id", employee.getId()); // Pass the id as a query parameter
			return "redirect:welcome";
		} else {
			return "login";
		}
	}

	@GetMapping("/welcome")
	public String showWelcomePage(Model model, @RequestParam("id") String id) {
	    // Create a placeholder employee object to bind the form data
	    Employee employee = new Employee();
	    model.addAttribute("employee", employee);
	    employee.setId(id);
	    String employeeName = employeeService.getEmployeeName(employee.getId());
	    model.addAttribute("employeeName", employeeName);

	    return "welcome";
	}

	@PostMapping("/getEmployeeName")
	public String getEmployeeName(@ModelAttribute("employee") Employee employee, Model model) {
	    String employeeName = employeeService.getEmployeeName(employee.getId());
	    model.addAttribute("employeeName", employeeName);
	    return "employeeName";
	}


	@GetMapping("/employees")
	public String showAllEmployees(Model model) {
		// Retrieve a list of all registered employees from the database using Hibernate

		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("employees", employees);
		return "employees";
	}

}
