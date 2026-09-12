package com.learning.banking.transactionservice;

import com.learning.banking.account.v1.GetAccountBalanceResponse;

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
}
