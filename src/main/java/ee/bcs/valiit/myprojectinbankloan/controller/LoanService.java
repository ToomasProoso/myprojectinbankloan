package ee.bcs.valiit.myprojectinbankloan.controller;


import org.springframework.stereotype.Service;

@Service
public class LoanService {

    static String loanCalc(String personalCode, double loanAmount, int loanPeriod) {

        //hard coded credit modifier
        double creditModifier;
        {

            if (personalCode.equals("49002010965")) {
                creditModifier = 0;
            } else if (personalCode.equals("49002010976")) {
                creditModifier = 100;
            } else if (personalCode.equals("49002010987")) {
                creditModifier = 300;
            } else {
                creditModifier = 1000;
            }
        }
        //  find the credit score of the customer
        double creditScore = (creditModifier / loanAmount) * loanPeriod;
        // check max approved amount for the customer
        double maxApprovedAmount = creditModifier * 60;
        {
            if (maxApprovedAmount >= 10000) {
                maxApprovedAmount = 10000;
            }
        }
        //check loan approved amount min 2000 €, max 10000
        double approvedAmount = creditModifier * loanPeriod;
        if (approvedAmount >= 10000) {
            approvedAmount = 10000;
        }
        if (approvedAmount < 2000) {
            approvedAmount = 2000;
        }
        if (loanAmount > maxApprovedAmount) {
            loanAmount = maxApprovedAmount;
        }
        //check loan approved period min 12, max 60 months
        double approvedPeriod = loanAmount / creditModifier;
        if (approvedPeriod >= 60) {
            approvedPeriod = 60;
        }
        if (approvedPeriod < 12) {
            approvedPeriod = 12;
        }
            //check loan amount min 2000 €, max 10000
        if (loanAmount >= 2000 & loanAmount <= 10000) {
            //check loan period min 12, max 60 months
            if (loanPeriod >= 12 & loanPeriod <= 60) {
                //if credit score is more than 1 approve loan
                if (creditScore >= 1) {
                    //if credit score is less than 1 but not 0 then calculate approved loan amount
                } else if (creditScore < 1 & creditScore != 0.0) {
                    return "If period is: " + loanPeriod + " months, then we can give you: " + approvedAmount +
                            " €, if you want to loan: " + loanAmount + " € then change period to: "
                            + Math.round(Math.ceil(approvedPeriod)) +
                            " months. Your max loan amount is: " + maxApprovedAmount + " €.";
                } else {
                    return "not approved";
                }
            } else if (creditScore == 0.0) {
                return "not approved";
            } else {
                return "Try again, loan period can be: 12 - 60 months";
            }
        } else if (creditScore == 0.0) {
            return "not approved";
        } else {
            return "Try again, loan amount can be: 2000 € - 10000 €";
        }
        //Approved loan and max what customer can get
        return "Approved you can get: " + loanAmount + " € but we can give you " + approvedAmount + " € for " + loanPeriod +
                " months. Maximum what we can loan to you is: " + maxApprovedAmount + " €.";
    }
}