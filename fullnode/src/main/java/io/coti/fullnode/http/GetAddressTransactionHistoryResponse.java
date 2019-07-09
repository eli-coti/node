package io.coti.fullnode.http;

import io.coti.basenode.data.TransactionData;
import io.coti.basenode.http.GetTransactionsResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GetAddressTransactionHistoryResponse extends GetTransactionsResponse {

    public GetAddressTransactionHistoryResponse(List<TransactionData> transactionsData) throws Exception {
        super(transactionsData);
    }
}

