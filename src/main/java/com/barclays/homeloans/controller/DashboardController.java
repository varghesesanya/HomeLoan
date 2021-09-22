package com.barclays.homeloans.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.homeloans.model.LoanRepayment;
import com.barclays.homeloans.service.DashboardService;
import com.barclays.homeloans.service.UserService;


@RestController
public class DashboardController {
	
    @Autowired
    private DashboardService dashboardService;

    
	@GetMapping("dashboard/{id}")
    public  List<LoanRepayment> dashboard(@PathVariable Long id) {  
		return dashboardService.Dashboard(id);
    }
}
