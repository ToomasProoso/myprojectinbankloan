package ee.bcs.valiit.myprojectinbankloan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    // http://localhost:8080/loan.html
    @CrossOrigin
    @PostMapping("loan")
    public String loanCalc(@RequestParam("loanAmount") Double loanAmount,
                           @RequestParam("loanPeriod") Integer loanPeriod,
                           @RequestParam("personalCode") String personalCode) {
               return loanService.loanCalc(personalCode, loanAmount, loanPeriod);
    }
}



