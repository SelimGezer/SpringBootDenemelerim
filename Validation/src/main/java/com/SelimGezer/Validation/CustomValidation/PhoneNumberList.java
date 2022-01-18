package com.SelimGezer.Validation.CustomValidation;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PhoneNumberList {
    PhoneNumber[] value();
}
