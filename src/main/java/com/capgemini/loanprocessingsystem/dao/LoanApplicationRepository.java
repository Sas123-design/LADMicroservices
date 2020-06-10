package com.capgemini.loanprocessingsystem.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.loanprocessingsystem.entity.ApplyLoan;
import com.capgemini.loanprocessingsystem.entity.User;

@Repository
public interface LoanApplicationRepository extends JpaRepository<ApplyLoan, Integer>{
	
	@Query("Select l from ApplyLoan l where status='requested'")
	List<ApplyLoan> getRequestedApplications();
	
	@Query("Select l from ApplyLoan l where status='approved'")
	List<ApplyLoan> getApprovedApplications();
	
	@Query("Select l from ApplyLoan l where status='rejected'")
	List<ApplyLoan> getRejectedApplications();
	
	@Query("select l from ApplyLoan l where status='rejected'")
	Page<ApplyLoan> findAllRejected(Pageable pageable);
	
	@Query("select l from ApplyLoan l where status='requested'")
	Page<ApplyLoan> findAllRequested(Pageable pageable);
	
	@Query("select l from ApplyLoan l where status='approved'")
	Page<ApplyLoan> findAllApproved(Pageable pageable);
	
	@Query(value = "select DISTINCT u from User u INNER JOIN u.applyLoan a where a.status='requested'")
	List<User> requestedApplications();
	
	@Query(value = "select DISTINCT u from User u INNER JOIN u.applyLoan a where a.status='approved'")
	List<User> approvedApplications();
	
	@Query(value = "select DISTINCT u from User u INNER JOIN u.applyLoan a where a.status='rejected'")
	List<User> rejectedApplications();
}
