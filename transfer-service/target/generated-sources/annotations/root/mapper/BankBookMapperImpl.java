package root.mapper;

import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import root.model.dto.BankBookDto;
import root.model.entity.BankBookEntity;
import root.model.entity.CurrencyEntity;
import root.model.entity.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-03T13:16:34+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class BankBookMapperImpl implements BankBookMapper {

    @Override
    public BankBookDto mapToDto(BankBookEntity bankBookEntity) {
        if ( bankBookEntity == null ) {
            return null;
        }

        String user = null;
        String currency = null;
        Integer id = null;
        String number = null;
        BigDecimal amount = null;

        user = bankBookEntityUserEmail( bankBookEntity );
        currency = bankBookEntityCurrencyName( bankBookEntity );
        id = bankBookEntity.getId();
        number = bankBookEntity.getNumber();
        amount = bankBookEntity.getAmount();

        BankBookDto bankBookDto = new BankBookDto( id, user, number, amount, currency );

        return bankBookDto;
    }

    @Override
    public BankBookEntity mapToEntity(BankBookDto bankBookDto) {
        if ( bankBookDto == null ) {
            return null;
        }

        BankBookEntity bankBookEntity = new BankBookEntity();

        bankBookEntity.setCurrency( bankBookDtoToCurrencyEntity( bankBookDto ) );
        bankBookEntity.setUser( bankBookDtoToUserEntity( bankBookDto ) );
        bankBookEntity.setId( bankBookDto.getId() );
        bankBookEntity.setNumber( bankBookDto.getNumber() );
        bankBookEntity.setAmount( bankBookDto.getAmount() );

        return bankBookEntity;
    }

    private String bankBookEntityUserEmail(BankBookEntity bankBookEntity) {
        if ( bankBookEntity == null ) {
            return null;
        }
        UserEntity user = bankBookEntity.getUser();
        if ( user == null ) {
            return null;
        }
        String email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String bankBookEntityCurrencyName(BankBookEntity bankBookEntity) {
        if ( bankBookEntity == null ) {
            return null;
        }
        CurrencyEntity currency = bankBookEntity.getCurrency();
        if ( currency == null ) {
            return null;
        }
        String name = currency.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected CurrencyEntity bankBookDtoToCurrencyEntity(BankBookDto bankBookDto) {
        if ( bankBookDto == null ) {
            return null;
        }

        CurrencyEntity currencyEntity = new CurrencyEntity();

        currencyEntity.setName( bankBookDto.getCurrency() );

        return currencyEntity;
    }

    protected UserEntity bankBookDtoToUserEntity(BankBookDto bankBookDto) {
        if ( bankBookDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail( bankBookDto.getUser() );

        return userEntity;
    }
}
