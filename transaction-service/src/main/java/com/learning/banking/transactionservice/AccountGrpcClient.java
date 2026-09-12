package com.learning.banking.transactionservice;

import com.learning.banking.account.v1.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import org.springframework.stereotype.Component;

@Component
public class AccountGrpcClient {

    private final ManagedChannel channel;
    private final AccountServiceGrpc.AccountServiceBlockingStub stub;

    public AccountGrpcClient() {

        this.channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        this.stub = AccountServiceGrpc.newBlockingStub(channel);
    }

    public GetAccountBalanceResponse getAccountBalance(String accountId) {

        GetAccountBalanceRequest request =
                GetAccountBalanceRequest.newBuilder()
                        .setAccountId(accountId)
                        .build();

        return stub.getAccountBalance(request);
    }

    public GetAccountDetailsResponse getAccountDetails(String accountId) {

        GetAccountDetailsRequest request =
                GetAccountDetailsRequest.newBuilder()
                        .setAccountId(accountId)
                        .build();

        return stub.getAccountDetails(request);
    }
}