package io.coti.dspnode.services;

import io.coti.basenode.communication.interfaces.IPropagationPublisher;
import io.coti.basenode.data.Hash;
import io.coti.basenode.data.NodeType;
import io.coti.basenode.data.TransactionData;
import io.coti.basenode.services.BaseNodeTransactionPropagationCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class TransactionPropagationCheckService extends BaseNodeTransactionPropagationCheckService {

    private static final long PERIOD_IN_SECONDS_BEFORE_PROPAGATE_AGAIN_DSP_NODE = 60;
    private static final int NUMBER_OF_RETRIES_DSP_NODE = 3;
    @Autowired
    private IPropagationPublisher propagationPublisher;

    @Override
    public void init() {
        super.init();
        unconfirmedReceivedTransactionHashesMap = new ConcurrentHashMap<>();
        updateRecoveredUnconfirmedReceivedTransactions();
    }

    @Override
    public void addUnconfirmedTransaction(Hash transactionHash) {
        addUnconfirmedTransaction(transactionHash, NUMBER_OF_RETRIES_DSP_NODE);
    }

    @Override
    public void removeTransactionHashFromUnconfirmed(Hash transactionHash) {
        removeTransactionHashFromUnconfirmedTransaction(transactionHash);
    }

    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    private void propagateUnconfirmedReceivedTransactions() {
        sendUnconfirmedReceivedTransactions(PERIOD_IN_SECONDS_BEFORE_PROPAGATE_AGAIN_DSP_NODE);
    }

    @Override
    public void sendUnconfirmedReceivedTransactions(TransactionData transactionData) {
        propagationPublisher.propagate(transactionData, Arrays.asList(
                NodeType.FullNode,
                NodeType.TrustScoreNode,
                NodeType.DspNode,
                NodeType.ZeroSpendServer,
                NodeType.FinancialServer,
                NodeType.HistoryNode));
    }
}