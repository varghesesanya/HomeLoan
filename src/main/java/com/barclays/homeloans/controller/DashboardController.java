package com.barclays.homeloans.controller;

import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.barclays.homeloans.model.Loan;
import com.barclays.homeloans.model.LoanRepayment;
import com.barclays.homeloans.repository.LoanRepaymentRepository;
import com.barclays.homeloans.repository.LoanRepository;
import com.barclays.homeloans.service.DashboardService;
import com.barclays.homeloans.service.UserService;


@RestController
public class DashboardController {
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private LoanRepaymentRepository loanRepaymentRepository;
    @Autowired
    private DashboardService dashboardService;

    
	@GetMapping("dashboard/{id}")
    public  List<LoanRepayment> dashboard(@PathVariable Long id) {  
		return dashboardService.Dashboard(id);
    }
	
	@GetMapping("/export/{loan_id}")
    public void exportToCSV(HttpServletResponse response, @PathVariable Long loan_id) throws IOException {
        response.setContentType("text/csv");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
         
		Optional <Loan> loan_list  = loanRepository.findById(loan_id);
		List <LoanRepayment> emi_list = loan_list.get().getLoanRepayment();
 
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"EMI", "Interest Amount", "Principal Amount", "Status", "OutStanding", "Date"};
        String[] nameMapping = { "emi", "interestAmount", "principalAmount", "status", "outstanding", "date"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (LoanRepayment entry : emi_list) {
            csvWriter.write(entry, nameMapping);
        }
         
        csvWriter.close();
         
    }
}
