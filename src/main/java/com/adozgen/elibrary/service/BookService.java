package com.adozgen.elibrary.service;

import com.adozgen.elibrary.dto.mapper.BookMapper;
import com.adozgen.elibrary.dto.request.BookRequest;
import com.adozgen.elibrary.dto.response.BookResponse;
import com.adozgen.elibrary.model.Author;
import com.adozgen.elibrary.model.Book;
import com.adozgen.elibrary.model.Category;
import com.adozgen.elibrary.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService implements IBookService {
    private final BookRepository bookRepository;
    private final IAuthorService authorService;
    private final ICategoryService categoryService;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, IAuthorService authorService, ICategoryService categoryService, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookMapper = bookMapper;
    }

    @Transactional
    @Override
    public BookResponse addBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setName(bookRequest.getName());
        if (bookRequest.getAuthorIds().isEmpty()){
            throw new IllegalArgumentException("En az bir yazar seçilmelidir");
        } else {
            List<Author> authors = new ArrayList<>();
            for (Long authorId : bookRequest.getAuthorIds()) {
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            book.setAuthors(authors);
        }
        if (bookRequest.getCategoryId() == null){
            throw new IllegalArgumentException("En az bir kategori seçilmelidir");
        }
        Category category = categoryService.getCategory(bookRequest.getCategoryId());
        book.setCategory(category);
        bookRepository.save(book);
        return bookMapper.bookToBookResponse(book);
    }

    @Override
    public BookResponse getBookById(Long bookId) {
        Book book = getBook(bookId);
        return bookMapper.bookToBookResponse(book);
    }

    @Override
    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(()->
                new IllegalArgumentException("kitap bulunamadı: "+bookId));
    }

    @Override
    public List<BookResponse> getBooks() {
        List<Book> books = StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return bookMapper.booksToBookResponses(books);
    }

    @Override
    public BookResponse deleteBook(Long bookId) {
        Book book = getBook(bookId);
        bookRepository.delete(book);
        return bookMapper.bookToBookResponse(book);
    }

    @Transactional
    @Override
    public BookResponse updateBook(Long bookId, BookRequest bookRequest) {
        Book book = getBook(bookId);
        book.setName(bookRequest.getName());
        if (!bookRequest.getAuthorIds().isEmpty()){
            List<Author> authors = new ArrayList<>();
            for (Long authorId: bookRequest.getAuthorIds()){
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            book.setAuthors(authors);
        }
        if (bookRequest.getCategoryId() != null){
            Category category = categoryService.getCategory(bookRequest.getCategoryId());
            book.setCategory(category);
        }
        return bookMapper.bookToBookResponse(book);
    }

    @Override
    public BookResponse addAuthorToBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if (author.getBooks().contains(author)){
            throw new IllegalArgumentException("Bu yazar zaten bu kitapda var");
        }
        book._addAuthor(author);
        author._addBook(book);
        return bookMapper.bookToBookResponse(book);
    }

    @Override
    public BookResponse removeAuthorFromBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if (!(author.getBooks().contains(book))){
            throw new IllegalArgumentException("Bu yazar zaten bu kitapda yok");
        }
        book._removeAuthor(author);
        author._removeBook(book);
        return bookMapper.bookToBookResponse(book);
    }

    @Override
    public BookResponse addCategoryToBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (Objects.nonNull(book.getCategory())){
            throw new IllegalArgumentException("Kitap zaten bu kategoride var");
        }
        book.setCategory(category);
        category._addBook(book);
        return bookMapper.bookToBookResponse(book);
    }

    @Override
    public BookResponse removeCategoryFromBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (!(Objects.nonNull(book.getCategory()))){
            throw new IllegalArgumentException("Kitap zaten bu kategoride yok");
        }
        book.setCategory(null);
        category._removeBook(book);
        return bookMapper.bookToBookResponse(book);
    }
}
