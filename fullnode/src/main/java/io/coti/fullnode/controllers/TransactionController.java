package io.coti.fullnode.controllers;

import io.coti.basenode.http.Response;
import io.coti.basenode.http.interfaces.IResponse;
import io.coti.basenode.services.TransactionIndexService;
import io.coti.fullnode.http.AddTransactionRequest;
import io.coti.fullnode.http.AddressRequest;
import io.coti.fullnode.http.GetAddressTransactionBatchRequest;
import io.coti.fullnode.http.GetTransactionRequest;
import io.coti.fullnode.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionIndexService transactionIndexService;

    @PutMapping()
    public ResponseEntity<Response> addTransaction(@Valid @RequestBody AddTransactionRequest addTransactionRequest) {
        return transactionService.addNewTransaction(addTransactionRequest);
    }

    @PostMapping()
    public ResponseEntity<IResponse> getTransactionDetails(@Valid @RequestBody GetTransactionRequest getTransactionRequest) {
        return transactionService.getTransactionDetails(getTransactionRequest.transactionHash);
    }

    @PostMapping(value = "/addressTransactions")
    public ResponseEntity<IResponse> getAddressTransactions(@Valid @RequestBody AddressRequest addressRequest) {
        return transactionService.getAddressTransactions(addressRequest.getAddress());
    }

    @PostMapping(value = "/addressTransactions/batch")
    public void getAddressTransactionBatch(@Valid @RequestBody GetAddressTransactionBatchRequest getAddressTransactionBatchRequest, HttpServletResponse response) {
        transactionService.getAddressTransactionBatch(getAddressTransactionBatchRequest, response);
    }

    @GetMapping(value = "/lastTransactions")
    public ResponseEntity<IResponse> getLastTransactions() {
        return transactionService.getLastTransactions();
    }

    @GetMapping(value = "/index")
    public ResponseEntity getCurrentIndex() {
        return ResponseEntity.ok(transactionIndexService.getLastTransactionIndexData());
    }
}