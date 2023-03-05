package com.sapient.bitcoin.validator;

import com.sapient.bitcoin.annotation.ValidCurrency;
import com.sapient.bitcoin.service.BitcoinService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Currency validator
 */
@Component
@AllArgsConstructor
@Log4j2
public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String>{

    private final BitcoinService bitcoinService;

    @Override
    public void initialize(ValidCurrency constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String currency, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Validating currency {}", currency);
        return bitcoinService.getSupportedCurrencies().get(currency) != null;
    }
}