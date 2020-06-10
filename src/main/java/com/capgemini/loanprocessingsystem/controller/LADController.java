package com.capgemini.loanprocessingsystem.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.loanprocessingsystem.dao.LoanApplicationRepository;
import com.capgemini.loanprocessingsystem.entity.ApplyLoan;
import com.capgemini.loanprocessingsystem.entity.LoanPrograms;
import com.capgemini.loanprocessingsystem.entity.User;
import com.capgemini.loanprocessingsystem.response.Response;
import com.capgemini.loanprocessingsystem.service.ClientService;
import com.capgemini.loanprocessingsystem.service.LoanApplicationService;
import com.capgemini.loanprocessingsystem.service.LoanProgramService;
import com.capgemini.loanprocessingsystem.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api") 
public class LADController {
	
	@Autowired
	private LoanProgramService loanProgramService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoanApplicationService applyLoanService;
	
	
	@Autowired
	private LoanApplicationRepository loanRepository;
	
	@GetMapping("/programs")
	public Response<List<LoanPrograms>> viewLoanPrograms(){
		List<LoanPrograms> list=loanProgramService.findAllPrograms();
		return new Response<List<LoanPrograms>>(false,"found",list);
	}
	
	@GetMapping("/getapproved")
	public Response<List<User>> getApprovedApplications(){
		List<User> list=userService.approvedApplications();
		return new Response<List<User>>(false, "approved applications", list);
	}
	
	@GetMapping("/getrejected")
	public Response<List<User>> getRejectedApplications(){
		List<User> list=userService.rejectedApplications();
		return new Response<List<User>>(false, "rejected applications", list);
	}
	
	@GetMapping("/getrequested")
	public Response<List<User>> getRequstedApplications(){
		List<User> list=userService.requestedApplications();
		return new Response<List<User>>(false, "requested applications", list);
	}
	
	@PutMapping("/makeapproved/{loanId}")
	public Response<ApplyLoan> makeApproved(@PathVariable int loanId){
		ApplyLoan application=applyLoanService.findApplicationById(loanId);
		application.setStatus("approved");
		ApplyLoan application1=applyLoanService.saveApplication(application);
		return new Response<ApplyLoan>(false, "Approved", application1); 
	}
	
	@PutMapping("/makerejected/{loanId}")
	public Response<ApplyLoan> makeRejecteded(@PathVariable int loanId){
		ApplyLoan application=applyLoanService.findApplicationById(loanId);
		application.setStatus("rejected");
		ApplyLoan application1=applyLoanService.saveApplication(application);
		return new Response<ApplyLoan>(false, "Rejected", application1); 
	}
		
	
}
