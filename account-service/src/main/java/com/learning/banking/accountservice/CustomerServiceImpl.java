package com.learning.banking.accountservice;

import com.learning.banking.account.v1.CustomerServiceGrpc;
import com.learning.banking.account.v1.GetCustomerDetailsRequest;
import com.learning.banking.account.v1.GetCustomerDetailsResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CustomerServiceImpl extends CustomerServiceGrpc.CustomerServiceImplBase {

    @Override
    public void getCustomerDetails(
            GetCustomerDetailsRequest request,
            StreamObserver<GetCustomerDetailsResponse> responseObserver) {

        String customerId = request.getId();
        System.out.println("Received request for customer: " + customerId);

        GetCustomerDetailsResponse response =
                GetCustomerDetailsResponse.newBuilder()
                        .setId(customerId)
                        .setDistrictCode("JHAPA")
                        .setDocumentType("PAN")
                        .setDocumentIdNumber("123456789")
                        .setFirstName("Rajan")
                        .setMiddleName("Mani")
                        .setLastName("Shrestha")
                        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
