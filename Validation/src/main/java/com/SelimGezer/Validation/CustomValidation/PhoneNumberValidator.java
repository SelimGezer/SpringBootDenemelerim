package com.SelimGezer.Validation.CustomValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber,String> {

   @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.startsWith("+90") && !s.isEmpty() && s.length()==13){
            return true;
        }
        return false;
    }
}
