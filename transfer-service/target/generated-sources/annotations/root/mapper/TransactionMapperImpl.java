package root.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import root.model.dto.TransactionDto;
import root.model.entity.BankBookEntity;
import root.model.entity.StatusEntity;
import root.model.entity.TransactionEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-01T10:07:56+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionEntity mapToEntity(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setSourceBankBookId( transactionDtoToBankBookEntity( transactionDto ) );
        transactionEntity.setTargetBankBookId( transactionDtoToBankBookEntity1( transactionDto ) );
        transactionEntity.setStatus( transactionDtoToStatusEntity( transactionDto ) );
        transactionEntity.setId( transactionDto.getId() );
        transactionEntity.setAmount( transactionDto.getAmount() );
        transactionEntity.setInitiationDate( transactionDto.getInitiationDate() );
        transactionEntity.setCompletionDate( transactionDto.getCompletionDate() );

        return transactionEntity;
    }

    @Override
    public TransactionDto mapToDto(TransactionEntity transactionEntity) {
        if ( transactionEntity == null ) {
            return null;
        }

        String sourceBankBookId = null;
        String targetBankBookId = null;
        String status = null;
        Integer id = null;
        BigDecimal amount = null;
        LocalDateTime initiationDate = null;
        LocalDateTime completionDate = null;

        sourceBankBookId = transactionEntitySourceBankBookIdNumber( transactionEntity );
        targetBankBookId = transactionEntityTargetBankBookIdNumber( transactionEntity );
        status = transactionEntityStatusName( transactionEntity );
        id = transactionEntity.getId();
        amount = transactionEntity.getAmount();
        initiationDate = transactionEntity.getInitiationDate();
        completionDate = transactionEntity.getCompletionDate();

        TransactionDto transactionDto = new TransactionDto( id, sourceBankBookId, targetBankBookId, amount, initiationDate, completionDate, status );

        return transactionDto;
    }

    protected BankBookEntity transactionDtoToBankBookEntity(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        BankBookEntity bankBookEntity = new BankBookEntity();

        bankBookEntity.setNumber( transactionDto.getSourceBankBookId() );

        return bankBookEntity;
    }

    protected BankBookEntity transactionDtoToBankBookEntity1(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        BankBookEntity bankBookEntity = new BankBookEntity();

        bankBookEntity.setNumber( transactionDto.getTargetBankBookId() );

        return bankBookEntity;
    }

    protected StatusEntity transactionDtoToStatusEntity(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        StatusEntity statusEntity = new StatusEntity();

        statusEntity.setName( transactionDto.getStatus() );

        return statusEntity;
    }

    private String transactionEntitySourceBankBookIdNumber(TransactionEntity transactionEntity) {
        if ( transactionEntity == null ) {
            return null;
        }
        BankBookEntity sourceBankBookId = transactionEntity.getSourceBankBookId();
        if ( sourceBankBookId == null ) {
            return null;
        }
        String number = sourceBankBookId.getNumber();
        if ( number == null ) {
            return null;
        }
        return number;
    }

    private String transactionEntityTargetBankBookIdNumber(TransactionEntity transactionEntity) {
        if ( transactionEntity == null ) {
            return null;
        }
        BankBookEntity targetBankBookId = transactionEntity.getTargetBankBookId();
        if ( targetBankBookId == null ) {
            return null;
        }
        String number = targetBankBookId.getNumber();
        if ( number == null ) {
            return null;
        }
        return number;
    }

    private String transactionEntityStatusName(TransactionEntity transactionEntity) {
        if ( transactionEntity == null ) {
            return null;
        }
        StatusEntity status = transactionEntity.getStatus();
        if ( status == null ) {
            return null;
        }
        String name = status.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
