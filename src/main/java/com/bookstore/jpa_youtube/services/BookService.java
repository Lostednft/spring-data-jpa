package com.bookstore.jpa_youtube.services;

import com.bookstore.jpa_youtube.Repositories.AuthorRepository;
import com.bookstore.jpa_youtube.Repositories.BookRepository;
import com.bookstore.jpa_youtube.Repositories.PublisherRepository;
import com.bookstore.jpa_youtube.dtos.BookModelDTO;
import com.bookstore.jpa_youtube.models.BookModel;
import com.bookstore.jpa_youtube.models.ReviewModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final PublisherRepository publisherRepository;


    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public BookModel saveBook (BookModelDTO bookModelDTO){

        var book = new BookModel();
        book.setTitle(bookModelDTO.title());
        book.setAuthor(new HashSet<>(authorRepository.findAllById(bookModelDTO.authorIds())));
        book.setPublisher(publisherRepository.findById(bookModelDTO.publisherId()).get());

        var review = new ReviewModel();
        review.setComment(bookModelDTO.reviewComment());
        review.setBook(book);

        book.setReview(review);

        return bookRepository.save(book);
    }


    public List<BookModel> getAllBooks(){

        return bookRepository.findAll();
    }

    public BookModel getBookByPublisherId (UUID publisherId)
    {
        return bookRepository.findBooksByPublisherId(publisherId);
    }

    @Transactional
    public void deleteBookById(UUID id){

        bookRepository.deleteById(id);
    }
}
