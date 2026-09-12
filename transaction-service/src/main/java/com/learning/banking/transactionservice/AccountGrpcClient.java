package com.learning.banking.transactionservice;

import com.learning.banking.account.v1.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import org.springframework.stereotype.Component;

@Component
public class AccountGrpcClient {

    private final ManagedChannel channel;
    private final AccountServiceGrpc.AccountServiceBlockingStub accountStub;
    private final CustomerServiceGrpc.CustomerServiceBlockingStub customerStub;

    public AccountGrpcClient() {

        this.channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        this.accountStub = AccountServiceGrpc.newBlockingStub(channel);
        this.customerStub = CustomerServiceGrpc.newBlockingStub(channel);
    }

    public GetAccountBalanceResponse getAccountBalance(String accountId) {

        GetAccountBalanceRequest request =
                GetAccountBalanceRequest.newBuilder()
                        .setAccountId(accountId)
                        .build();

        return accountStub.getAccountBalance(request);
    }

    public GetAccountDetailsResponse getAccountDetails(String accountId) {

        GetAccountDetailsRequest request =
                GetAccountDetailsRequest.newBuilder()
                        .setAccountId(accountId)
                        .build();

        return accountStub.getAccountDetails(request);
    }

    public GetCustomerDetailsResponse getCustomerDetails(String customerId) {

        GetCustomerDetailsRequest request =
                GetCustomerDetailsRequest.newBuilder()
                        .setId(customerId)
                        .build();

        return customerStub.getCustomerDetails(request);
    }
}