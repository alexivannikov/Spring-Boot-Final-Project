package root.validation;

import root.repository.CurrencyEntityRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {
    private final CurrencyEntityRepository currencyEntityRepository;

    public CurrencyValidator(CurrencyEntityRepository currencyEntityRepository){
        this.currencyEntityRepository = currencyEntityRepository;
    }

    public boolean isValid(String currencyName, ConstraintValidatorContext constraintValidatorContext){
        return currencyEntityRepository.findByName(currencyName) != null;
    }
}
