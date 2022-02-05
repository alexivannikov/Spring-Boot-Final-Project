package root.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import root.mapper.BankBookMapper;
import root.model.dto.BankBookDto;
import root.model.entity.BankBookEntity;
import root.model.entity.CurrencyEntity;
import root.model.exception.BankBookException;
import root.repository.BankBookEntityRepository;
import root.repository.CurrencyEntityRepository;
import root.repository.UserEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@RequiredArgsConstructor
@Component
@Validated
public class BankBookServiceImpl implements BankBookService{
    private final BankBookEntityRepository bankBookEntityRepository;
    private final UserEntityRepository userEntityRepository;

    @Autowired
    private final BankBookMapper bankBookMapper;

    public List<BankBookDto> findAllBankBookDtoOfUser(String userEmail) {
        List <BankBookEntity> bankBookEntities = bankBookEntityRepository.findByUser(userEntityRepository.findByEmail(userEmail));
        List <BankBookDto> bankBookDtos = new CopyOnWriteArrayList<>();

        if(bankBookEntities.isEmpty()){
            throw new BankBookException("У пользователя email = " + userEmail + " нет счетов");
        }
        else{
            for(BankBookEntity b: bankBookEntities){
                bankBookDtos.add(bankBookMapper.mapToDto(b));
            }

        }

        return bankBookDtos;
    }
}
