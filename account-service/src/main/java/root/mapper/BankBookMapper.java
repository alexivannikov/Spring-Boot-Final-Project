package root.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import root.model.dto.BankBookDto;
import root.model.entity.BankBookEntity;

@Mapper(componentModel = "spring")
public interface BankBookMapper {
    @Mappings({@Mapping(target = "user", source = "user.email"),
              @Mapping(target = "currency", source = "currency.name")})
    BankBookDto mapToDto(BankBookEntity bankBookEntity);

    @Mappings({@Mapping(target = "currency.name", source = "currency"),
               @Mapping(target = "user.email", source = "user")})
    BankBookEntity mapToEntity(BankBookDto bankBookDto);
}
