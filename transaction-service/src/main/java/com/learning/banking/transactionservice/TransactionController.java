package com.learning.banking.transactionservice;

import com.learning.banking.account.v1.GetAccountBalanceResponse;

import com.learning.banking.account.v1.GetAccountDetailsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final AccountGrpcClient accountGrpcClient;

    public TransactionController(AccountGrpcClient accountGrpcClient) {
        this.accountGrpcClient = accountGrpcClient;
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
