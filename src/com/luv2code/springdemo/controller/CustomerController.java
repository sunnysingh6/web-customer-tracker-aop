package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("customer")
public class CustomerController {
	
	
	//Inject Customer Service into controller
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel)
	{
		
		//get the customer from service
		List<Customer> theCustomers=customerService.getCustomers();
		
		//Add the customer to Spring MVC model
		theModel.addAttribute("customers", theCustomers);
		
		
		return "list-customers";
	}	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		
		Customer thecustomer=new Customer();
		
		theModel.addAttribute("customer",thecustomer);
		
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
	{
		
		customerService.saveCustomer(theCustomer);
		
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForupdate(@RequestParam("customerId") int theId,Model theModel)
	{
		//get the customer from the database
		Customer theCustomer=customerService.getCustomer(theId);
		
		//set the customer as model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		
		//send over to our form
		
		
		
		
		return "customer-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		//delete the customer
		
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
		
	}
	@GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                    Model theModel) {

        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
                
        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";        
    }
	
	

}
