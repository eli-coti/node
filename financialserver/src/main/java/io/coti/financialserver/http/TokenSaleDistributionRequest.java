package io.coti.financialserver.http;

import io.coti.basenode.http.Request;
import io.coti.financialserver.data.TokenSaleDistributionData;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class TokenSaleDistributionRequest extends Request {

    @NotNull
    private @Valid TokenSaleDistributionData tokenSaleDistributionData;
}
