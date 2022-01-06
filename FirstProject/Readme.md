# FirstProject
 
Proje Mysql veritabanı ve Spring Framework 2.6.2 versiyonu kullanılarak oluşturulmuştur. İlgili configrasyonları yapmak için application.properties dosyasını ziyaret ediniz.

> Mysql üzerinde çalışmak için
> * Mysql ayarlarınız varsayılan ise application.properties olduğu gibi bırakılabilir. Eğer Mysql hostunuzda bir username ve password belirlediyseniz **username ve password** alanları düzenleyiniz.
> * Proje üzerinde direk çalışmak için mysql hostunuz üzerinde **deneme** adında bir veritabanı oluşturunuz.
> * Veritabanını bazı değerlerle doldurmak için. application.properties içerindeki **ddl-auto=create** modunda iken **Configure/InialValue sınıfını** ziyaret ediniz. Sınıf üzerindeki @Configuration anotasyonunu yorum olmaktan çıkarın. Ve projeyi ayağa kaldırın. Bu sayede veritabanına bazı kayıtlar eklemiş olacaksınız. Sonrasında tekrar eklenmemesi için **@Configuration anotasyonunu yorum haline getirip** **ddl-auto=update** hale getirmelisiniz. Aksi takdirde her seferinde veribanına eklemeye devam edecektir.  

Mysql veritabanınızdaki tabloları (Proje default olarak InnoDB motoru üzerinde oluşturacaktır.)
> **InnoDB** motoru üzerinde oluşturmak istiyorsanız.
> 
  **hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect**
  
> **MyIsam** motoru üzerinde oluşturmak istiyorsanız.
> 
  **spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect** 
  
 olarak değiştiriniz.
