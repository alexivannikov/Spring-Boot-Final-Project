package root.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import root.mapper.BankBookMapper;
import root.model.dto.BankBookDto;
import root.model.entity.BankBookEntity;
import root.model.entity.CurrencyEntity;
import root.model.exception.BankBookException;
import root.repository.BankBookEntityRepository;
import root.repository.CurrencyEntityRepository;
import root.repository.UserEntityRepository;

import java.math.BigDecimal;
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

    @Value("${convert-url}")
    private String convertUrl;

    public List<BankBookDto> findAllBankBookDtoOfUser(String userEmail) {
        List <BankBookEntity> bankBookEntities = bankBookEntityRepository.findByUser(userEntityRepository.findByEmail(userEmail));
        List <BankBookDto> bankBookDtos = new CopyOnWriteArrayList<>();

        if(bankBookEntities.isEmpty()){
            throw new BankBookException("У пользователя email = " + userEmail + " нет счетов");
        }
        else{
            for(BankBookEntity b: bankBookEntities) {
                if (b.getCurrency().getName().equals("RUB")) {
                    b.setAmountRUR(b.getAmount());
                    bankBookDtos.add(bankBookMapper.mapToDto(b));
                } else {
                    String transferDetails = b.getAmount() + ",RUB";
                    String convertResult = convert(transferDetails);
                    String resultAmountAsString = convertResult.substring(convertResult.indexOf("\"resultAmount\":") + "\"resultAmount\":".length(), convertResult.indexOf(",\"targetCurrency\":"));
                    Double resultAmountAsDouble = Double.valueOf(resultAmountAsString);
                    BigDecimal resultAmount = new BigDecimal(resultAmountAsDouble);

                    b.setAmountRUR(resultAmount);
                    bankBookDtos.add(bankBookMapper.mapToDto(b));
                }
            }

        }

        return bankBookDtos;
    }

    private String convert(String transferDetails){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>(transferDetails);
        String answer = restTemplate.postForObject(convertUrl, entity, String.class);

        return answer;
    }
}
