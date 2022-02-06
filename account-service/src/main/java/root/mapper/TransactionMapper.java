package root.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import root.model.dto.TransactionDto;
import root.model.entity.TransactionEntity;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source = "sourceBankBookId", target = "sourceBankBookId.number")
    @Mapping(source = "targetBankBookId", target = "targetBankBookId.number")
    @Mapping(source = "status", target = "status.name")
    TransactionEntity mapToEntity(TransactionDto transactionDto);

    @Mapping(target = "sourceBankBookId", source = "sourceBankBookId.number")
    @Mapping(target = "targetBankBookId", source = "targetBankBookId.number")
    @Mapping(target = "status", source = "status.name")
    TransactionDto mapToDto(TransactionEntity transactionEntity);
}
