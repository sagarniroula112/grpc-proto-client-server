package com.learning.banking.accountservice;

import com.learning.banking.account.v1.AccountServiceGrpc;
import com.learning.banking.account.v1.GetAccountBalanceRequest;
import com.learning.banking.account.v1.GetAccountBalanceResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AccountServiceImpl
        extends AccountServiceGrpc.AccountServiceImplBase {

    @Override
    public void getAccountBalance(
            GetAccountBalanceRequest request,
            StreamObserver<GetAccountBalanceResponse> responseObserver) {

        String accountId = request.getAccountId();

        System.out.println("Received request for account: " + accountId);

        GetAccountBalanceResponse response =
                GetAccountBalanceResponse.newBuilder()
                        .setAccountId(accountId)
                        .setBalanceInPaisa(125000)
                        .setCurrency("NPR")
                        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}