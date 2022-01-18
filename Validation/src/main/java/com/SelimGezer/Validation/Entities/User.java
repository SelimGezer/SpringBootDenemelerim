package com.SelimGezer.Validation.Entities;

import com.SelimGezer.Validation.CustomValidation.PhoneNumber;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true,name = "Unique_Username")
    @Length(max = 25,message = "25 karakterden fazla giremezsiniz!")
    String username;

    @Size(min=5,max=25,message="Şifre {min} ve {max} arasında uzunluğa sahip olmalıdır!")
    String password;

    @Size(min=1,max=1,message="min:{min} ile max:{max} karakterden fazla giriş yapamazsınız!")
    @Column(length = 1)
    String gender;


    @Email(message = "Geçersiz bir email girdiniz!")
    String email;

    @Min(value = 18,message = "18 yaş altındaki kullanıcılarımız sisteme dahil edilmemektedir.")
    int age;

    @NotBlank(message = "Bu alanı boş bırakamazsınız!")
    String birthPlace;

    @Digits(integer = 4, fraction = 2,message = "Tam kısım için max {integer} karakter, vigülden sonra için max {fraction} karakter kullanınız!")
    private BigDecimal price;

    @Range(min = 10,max = 25,message = "Range({min},{max}) arasında seçim yapınız!")
    int workYear;

    @PhoneNumber(message = "Lütfen alan koduyla birlikte telefon numaranızı giriniz!")
    String phoneNumber;

    @Past(message = "Lütfen geçerli bir zaman değeri giriniz!")
    Date date;


}
