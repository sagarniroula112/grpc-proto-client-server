package com.learning.banking.transactionservice;

import com.learning.banking.account.v1.GetAccountBalanceResponse;

import com.learning.banking.account.v1.GetAccountDetailsResponse;
import com.learning.banking.account.v1.GetCustomerDetailsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final AccountGrpcClient accountGrpcClient;

    public TransactionController(AccountGrpcClient accountGrpcClient) {
        this.accountGrpcClient = accountGrpcClient;
    }

    @GetMapping("/customer/{customerId}/details")
    public String getCustomerDetails(@PathVariable String customerId) {

        GetCustomerDetailsResponse response =
                accountGrpcClient.getCustomerDetails(customerId);

        return "Customer ID: " + response.getId()
                + ", First Name: " + response.getFirstName()
                + ", Middle Name: " + response.getMiddleName()
                + ", Last Name: " + response.getLastName()
                + ", District Code: " + response.getDistrictCode()
                + ", Document Type: " + response.getDocumentType()
                + ", Document Number: " + response.getDocumentIdNumber();
    }

    @GetMapping("/transactions/account/{accountId}/balance")
    public String getAccountBalance(@PathVariable String accountId) {

        GetAccountBalanceResponse response =
                accountGrpcClient.getAccountBalance(accountId);

        return "Account ID: " + response.getAccountId()
                + ", Balance: " + response.getBalanceInPaisa()
                + ", Currency: " + response.getCurrency();
    }

    @GetMapping("/transactions/account/{accountId}/details")
    public String getAccountDetails(@PathVariable String accountId) {

        GetAccountDetailsResponse response =
                accountGrpcClient.getAccountDetails(accountId);

        return "Account ID: " + response.getAccountId()
                + ", Scheme Code: " + response.getSchemeCode()
                + ", Customer Account Type: " + response.getCustomerAccountType()
                + ", Branch Code: " + response.getBranchCode();
    }
}
