package io.coti.financialserver.model;

import io.coti.basenode.model.Collection;
import io.coti.financialserver.data.UserDisputesData;
import org.springframework.stereotype.Service;

@Service
public class MerchantDisputes extends Collection<UserDisputesData> {

    public void init() {
        super.init();
    }
}