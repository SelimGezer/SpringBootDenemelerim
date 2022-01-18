# Validation
> ### Javax.Validation tarafından sağlanan anotasyonlar
>![javax](https://user-images.githubusercontent.com/72503092/149967797-2b4c0861-ac48-4f22-8d96-471436fb7881.png)


<!--<img  src=![javax](C:/Users/Selim Gezer/Desktop/javax.png) width="10" height="10"></img>--> 

> ### Hibernate.validator tarafından sağlanan anotasyonlar
>![hiber](https://user-images.githubusercontent.com/72503092/149967922-f4aebfb2-3a48-4d63-88bd-d1c8250aad65.png)


> ### Sık Kullanılan Anotasyonlar
* Anotasyonların hepsinde **message** değerini vererek geçerli validasyon şartlarına uyulmadığında ne mesaj vermesi gerektiğini söyleyebilirsiniz.
* Anotasyonların bütün değerleri verilmek zorunda değildir. Sadece kontrol edilmesi istenen duruma uygun parametreye değer kısmı verilebilir.

  * **@Size(min,max)** : Belirtilen min,max değerleri arası uzunluktaki girişlere izin verir.
  * **@Min(value)** : Belirtilen value değerine göre girişin bu değerden daha küçük olmasını engeller.
  * **@Max(value)** : Belirtilen value değerine göre girişin bu değerden daha büyük olmasını engeller.
  * **@Email()** : Girilen değerin bir email adresi olup olmadığını kontrol eder.
  * **@NotBlank()** : Belirtilen alanın boş bırakılamayacağını belirtir.
  * **@Lenght(min,max)** : Belirtilen ifadenin uzunluğunun min ve max değerleri arasında olup olmadığını kontrol eder.
  * **@Digit(integer,fration)** : Girilen değerin tam kısmının maximum integer haneli, hassasiyetini(virgülden sonraki kısmı) ise maximum fraction kadar olup olmadığını kontrol eder.
  * **@Past()** : Belirtilen tarih değerinin geçmiş zaman olup olmadığını kontrol eder. Yine bunun diğer variantları için @PastOrPresent, @Future, @FutureOrPresent anotasyonları kullanılabilir.

> ### Validasyon işlemleri sonucunda oluşan hataları görmek için
``` 
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));
            
        return errors;
    }
```
``` 
    @PostMapping()
    public ResponceBodyWithData addUser(@Valid @RequestBody User user){
        return userEntityService.addUser(user);
    }
```

Bu methodu Controller sınıfımız içerisine ekleyip ilgili validasyon işlemi yapacağımız Mapping işleminde ise **@Valid** anotasyonu kullanmamız gerekir. 
Methodu inceleyecek olursak
* **@ResponceStatus** ile bu hataların Bad_Request olarak döneceği belirtilmiştir.
* **@ExceptionHandler(MethodArgumentNotValidException.class)** bu türde hataların yakalanacağı belirtilmiştir. Bunun nedeni validasyon işlemleri sonucunda bir hata oluşursa **MethodArgumentNotValidException** tipinde bir hata fırlatmasıdır.
* Methodun içerisinde ise bir map yardımıyla tüm oluşan validasyon hataları toplanmış ve geri dönülmüştür.
  * **error.getField()** hangi alanda hata olduğunu, **error.getDefaultMessage()** ise anostasyonları eklerken **message** parametresine ne geçtiğimizi temsil eder.

> ### Custom Validation Oluşturmak
>Kendi validayonlarımızı oluşturmadan önce herhangi bir validasyon anotasyonunun üzerine gelip incelediğimizde aşağıdaki yapıyla karşılaşırız.
>
![validation](https://user-images.githubusercontent.com/72503092/149984073-529273d4-2db1-4112-9db3-62dc4b0d5d79.png)

* Yapıyı incelediğimizde **@Documented** dışında 4 anotasyon bizi karşılar bunlar;
   >  * **Constraint()** : Bu kısım ilgili anotasyonun hangi sınıf tarafından valid edileceğini temsil eder.
   >  * **Target()**     : Bu kısım ilgili anotasyonun hangi seviyelerde kullanılabileceğini temsil eder. Örneğin: **ElementType.METHOD** bu anotasyonun method düzeyine kullanılabileceğini temsil ederken, **ElementType.FIELD** bu anotasyonun alan bazında kullanılabileceğini ifade eder. Bunlardan birkaçı birlikte kullanılabilir.
   >  * **Retention()**  : Bu kısım bu anotasyon hatasının hangi durumda ortaya çıkacağını belirtir.
   >  * **Repeatable()** : Bu kısım aynı anostasyonun birden fazla aynı methoda,field'a vb... yapıya uygulanıp uygulanamayacağını belirler.
* Anatosyonun yazıldığı sınıfı incelersek içerisinde aşağıdaki methodlar görülecektir.
  > * **message()**
  > * **groups()**
  > * **payload()**
<br/><br/>
  > Bu 3 method olmadan validasyon anotasyonları oluşturamayız. Bunun dışında flag,regex gibi farklı methodlarda görünebilir fakat bunlar zorunlu değildir.
  > Zorunlu olan methodları custom validasyon yazarken tanımlamazsak **javax.validation.ConstraintDefinitionException** alırız.
   

### Örnek: (Projenin CustomValidation paketinin altında bulabilirsiniz.)
Bir örnek üzerinden kendi custom validasyon anotasyonumuzu oluşturalım : Bir telefon numarası validasyonu yapacağımızı düşünelim.
* İlk olarak **@interface** tanımlı bir birim oluşturalım. Burada belirttiğimiz isim anotasyonumuzun ismi olacaktır(PhoneNumber)
```
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = PhoneNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(PhoneNumberList.class)
public @interface PhoneNumber {

   String message() default "Invalid phone number";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};

}
```
* @Target yapımızı incelediğimizde anotasyonumuzun Field ve Method düzeyinde uygulanabileceği görülebilir. 
* @Constraint kısmında bu anotasyon kullanıldığında hangi koşullara göre validasyon yapılacağını belirtiriz. Bizim örneğimizde aşağıdaki sınıf kullanılmaktadır.
O yüzden buradaki parametreye o sınıfa eşlenmiştir. **validatedBy=PhoneNumberValidator.class**
* @Retention kısmında validasyon hatalarının Runtime da etki edeceği görülebilir.
* @Repeated kısmına yazının ilerleyen kısımlarında değineceğim.
```
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber,String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.startsWith("+90") && !s.isEmpty() && s.length()==13){
            return true;
        }
        return false;
    }
}
```
Bu sınıfta dikkat etmemiz gereken nokta sınıfın **ConstraintValidator<PhoneNumber,String>** interfaceni implements etmesidir. Buradaki ilk parametre oluşturduğumuz anotasyon, ikinci parametre ise anotasyonun uygulanacağı verinin tipini belirtir. Bu tip yanlış olduğunda anotasyonu uygulasak bile çalışmayacaktır. Bu interface implement ettiğimizde 2 methodu override etmek isteyecektir. Bunlar;
* **initialize()** : Bazı ilk değer atamaları yapabildiğimiz kısmı ifade eder.
* **isValid()** : Validasyon koşullarının yazılacağı kısmı ifade eder.

Bizim örneğimizde initialize kullanılmamıştır.isValid kısmında ise bir if koşuluyla telefon numarasının +90 alan koduyla başlayıp başlamadığı, boş olup olmadığını ve karakter sayısının 13 hane olup olmadığı kontrol edilip sonucuna göre bir boolean ifade dönülmüştür. 
Bu yapıyıda oluşturduktan sonra artık anotasyonumuzu kullanabiliriz.

    @PhoneNumber(message = "Lütfen boş bırakmayın ve alan koduyla birlikte telefon numaranızı giriniz!")
    String phoneNumber;

Şimdi gelelim @Repeated kısmına eğer yapıdan @Repeated kısmını çıkarırsanız yine başarılı bir şekilde çalıştığını göreceksiniz. O zaman @Repeated bize nasıl bir katkı sağlıyor. Şöyle

    @PhoneNumber(message = "Lütfen boş bırakmayın ve alan koduyla birlikte telefon numaranızı giriniz!")
    @PhoneNumber(message = "Lütfen boş bırakmayın ve alan koduyla birlikte telefon numaranızı giriniz!")
    String phoneNumber;

Bu şekilde bir kullanıma giderseniz hata oluştuğunu göreceksiniz. Eğer bunun gibi oluştuduğumuz anotasyonları birden fazla kullanmak istersek @Repeated ile bunu belirmeliyiz.

Örneğimizde şu şekildeydi;
        
    @Repeatable(PhoneNumberList.class)

Burada yeni bir @interface yapısındaki **PhoneNumberList.class** sınıfı işaret edilir. Bu sınıfı direk **PhoneNumber** anotasyonumuzu oluşturduğumuz sınıfada dahil edebiliriz veya yeni sınıf olarakta oluşturup kullanabiliriz Sınıfın public olmasına dikkat edin aksi takdirde hata oluşacaktır. Örneğimizde yeni bir sınıf olarak oluşturduk. Aşağıdan inceleyebilirsiniz.
```
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PhoneNumberList {
    PhoneNumber[] value();
}
```
Buradaki @Target biriminin oluştuduğumuz anotasyondaki @Target birimiyle,@Retention biriminin ise anotasyonumuzdaki @Retention ile aynı olmalıdır. Aksi takdirde hata alınacaktır.
Diğer bir önemli kısım ise ismi **value** olan method bulunması gereğidir. Yine bu methoduda sistem bulamadığında hata verecektir. **value()** methodunun dikkat edilmesi gereken tarafı ise dönüş türünün oluşturduğumuz **PhoneNumber** anotasyonu türünde ve bir **dizi** olması gerektiğidir. Bu şekilde artık aynı anotasyonu birden çok defa aynı yapıya uygulayabiliriz.

    @PhoneNumber(message = "Lütfen boş bırakmayın ve alan koduyla birlikte telefon numaranızı giriniz!")
    @PhoneNumber(message = "Lütfen boş bırakmayın ve alan koduyla birlikte telefon numaranızı giriniz!")
    String phoneNumber;

Artık yukarıdaki gibi bir yapıyı hata almadan kullanabilirsiniz.
