package io.coti.financialserver.http;

import io.coti.basenode.http.BaseResponse;
import io.coti.financialserver.http.data.DisputeEventResponseData;
import lombok.Data;

import java.util.List;

@Data
public class GetDisputeHistoryResponse extends BaseResponse {
    private List<DisputeEventResponseData> disputeHistory;

    public GetDisputeHistoryResponse(List<DisputeEventResponseData> disputeHistory) {
        super();

        this.disputeHistory = disputeHistory;
    }
}
