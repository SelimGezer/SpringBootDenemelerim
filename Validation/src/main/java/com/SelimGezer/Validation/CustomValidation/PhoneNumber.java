package com.SelimGezer.Validation.CustomValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Repeatable(PhoneNumberList.class)
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

   String message() default "Invalid phone number";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};

}
