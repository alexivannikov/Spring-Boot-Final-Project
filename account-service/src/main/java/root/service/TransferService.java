package root.service;

import root.model.dto.TransactionDto;

public interface TransferService {
    public TransactionDto transfer(TransactionDto transactionDto);
}
