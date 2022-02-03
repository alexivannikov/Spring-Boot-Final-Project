package root.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import root.mapper.BankBookMapper;
import root.mapper.TransactionMapper;
import root.model.dto.TransactionDto;
import root.model.entity.BankBookEntity;
import root.model.entity.TransactionEntity;
import root.model.exception.TransferException;
import root.repository.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
@Validated
public class TransferServiceImpl implements TransferService {
    private final TransactionEntityRepository transactionEntityRepository;
    private final BankBookEntityRepository bankBookEntityRepository;
    private final CurrencyEntityRepository currencyEntityRepository;
    private final StatusEntityRepository statusEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final TransactionMapper transactionMapper;

    @Value("${spring.security.oauth2.resource-server.jwt.issuer-uri}")
    private String tokenUri;

    public TransactionDto transfer(TransactionDto transactionDto) {
        LocalDateTime startTransfer = LocalDateTime.now();

        BankBookEntity sourceBankBook = bankBookEntityRepository.findByNumber(transactionDto.getSourceBankBookId());
        BankBookEntity targetBankBook = bankBookEntityRepository.findByNumber(transactionDto.getTargetBankBookId());

        TransactionEntity t = transactionMapper.mapToEntity(transactionDto);

        if (sourceBankBook == null || targetBankBook == null) {
            LocalDateTime endTransfer = LocalDateTime.now();

            t.setSourceBankBookId(sourceBankBook);
            t.setTargetBankBookId(targetBankBook);
            t.setStatus(statusEntityRepository.findByName("declined"));
            t.setInitiationDate(startTransfer);
            t.setCompletionDate(endTransfer);

            transactionEntityRepository.save(t);

            throw new TransferException("Перевод отклонен: счет не существует!");
        } else if (sourceBankBook.getAmount().floatValue() < transactionDto.getAmount().floatValue()) {
            LocalDateTime endTransfer = LocalDateTime.now();

            t.setSourceBankBookId(sourceBankBook);
            t.setTargetBankBookId(targetBankBook);
            t.setStatus(statusEntityRepository.findByName("declined"));
            t.setInitiationDate(startTransfer);
            t.setCompletionDate(endTransfer);

            transactionEntityRepository.save(t);

            throw new TransferException("Перевод отклонен: на счете недостаточно средств");
        } else if (!currencyEntityRepository.findByName(sourceBankBook.getCurrency().getName()).getName().
                equals(currencyEntityRepository.findByName(targetBankBook.getCurrency().getName()).getName())) {
            LocalDateTime endTransfer = LocalDateTime.now();

            t.setSourceBankBookId(sourceBankBook);
            t.setTargetBankBookId(targetBankBook);
            t.setStatus(statusEntityRepository.findByName("declined"));
            t.setInitiationDate(startTransfer);
            t.setCompletionDate(endTransfer);

            transactionEntityRepository.save(t);

            throw new TransferException("Перевод отклонен: валюты счетов не совпадают!");
        } else {
            sourceBankBook.setAmount(sourceBankBook.getAmount().subtract(transactionDto.getAmount()));
            bankBookEntityRepository.save(sourceBankBook);
            targetBankBook.setAmount(targetBankBook.getAmount().add(transactionDto.getAmount()));
            bankBookEntityRepository.save(targetBankBook);

            LocalDateTime endTransfer = LocalDateTime.now();

            t.setSourceBankBookId(sourceBankBook);
            t.setTargetBankBookId(targetBankBook);
            t.setStatus(statusEntityRepository.findByName("successful"));
            t.setInitiationDate(startTransfer);
            t.setCompletionDate(endTransfer);

            transactionEntityRepository.save(t);
        }

        TransactionDto tDto = transactionMapper.mapToDto(t);

        return tDto;
    }
}
