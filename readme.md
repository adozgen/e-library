

Tablo ilişkilerinin nasıl olduğuna bakalım.

    1-OneToOne İlişkisi

Zipcodes ve Cities tabloları arasındaki ilişki bu ilişkiye örnektir.

Entityler arasındaki ilişki tanımlamaları ise şu şekilde olacaktır.
    
    Zipcode.java

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

Çift yönlü ilişki tanımlamak istiyorsanız City entity içindede tanımlama yapmak gerekir.
    
    City.java

    @OneToOne(mappedBy = "city", cascade = CascadeType.ALL)
    private Zipcode zipcode;


Kullanımı ise şu şekilde olacaktır. 

    Zipcode üzerinden city bilgilerine erişmek
    zipcode.getCity() ile ilgili zipcode ait city verisini alırız.

    Çift yönlü ilişki tanımlaması yaptığımız için City üzerinden de Zipcode verisine erişim sağlayabiliriz.
    city.getZipcode() ile ilgilil citye ait zipcode verisini alabiliriz. 

OneToOne ilişki tanımlaması bu şekildedir.

    2-ManyToOne İlişkisi

Zipcodes ve Authors ile Books ve Categories tabloları arasındaki ilişki bu ilişkiye örnektir.

Entityler arasındaki ilişki tanımlamaları ise şu şekilde olacaktır.

    Author.java

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "zipcode_id")
    private Zipcode zipcode;

Çift yönlü ilişki tanımlamak istiyorsanız Zipcode entity içindede tanımlama yapmak gerekir.

    Zipcode.java

    @OneToMany(mappedBy = "zipcode", cascade = CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();


Kullanımı ise şu şekilde olacaktır.

    Author üzerinden zipcode bilgisine erişmek
    author.getZipcode() ile ilgili authora ait zipcode verisini alırız.

    Çift yönlü ilişki tanımlaması yaptığımız için Zipcode üzerinden de Author verilerine erişim sağlayabiliriz.
    zipcode.getAuthors() ile ilgilil zipcode ait authors verilerini alabiliriz.

Books ve Categories ilişkileride benzer tanımlamaları içermektedir.

ManyToOne ve OneToMany ilişki tanımlaması bu şekildedir.


    3-ManyToMany İlişkisi


Books ve Authors tabloları arasındaki ilişki bu ilişkiye örnektir.

Entityler arasındaki ilişki tanımlamaları ise şu şekilde olacaktır.

    Book.java

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new ArrayList<>();

Bu tanımlama bize db de book_author pivot tablosunu oluşturacaktır.
Alanları ise id, book_id, author_id olacaktır.

Çift yönlü ilişki Author entity içindede tanımlamak yapmak gerekir.

    Author.java
    
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();


Kullanımı ise şu şekilde olacaktır.

    Author üzerinden book bilgilerine erişmek
    author.getBooks() ile ilgili authora ait book verilerini alırız.

    Book üzerinden author bilgilerine erişmek
    book.getAuthors() ile ilgili booka ait author verilerini alırız.

ManyToMany ilişki tanımlaması bu şekildedir.


Bu projede bu tablo ilişklerine ait crud işlemlerini içeren örnek bir rest api yapılmıştır.

Swagger üzerinden test edebilirsiniz.


![swagger1.png](src%2Fmain%2Fresources%2Fstatic%2Fswagger1.png)


![swagger2.png](src%2Fmain%2Fresources%2Fstatic%2Fswagger2.png)


![swagger3.png](src%2Fmain%2Fresources%2Fstatic%2Fswagger3.png)