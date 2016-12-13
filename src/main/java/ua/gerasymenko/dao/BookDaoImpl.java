package ua.gerasymenko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.gerasymenko.model.Book;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private static final Logger LOG = LoggerFactory.getLogger(BookDaoImpl.class);


    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(book);
        LOG.info("Book successfully saved, Book info: " + book);


    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(book);
        LOG.info("Book successfully updated, Book info: " + book);
    }

    @Override
    public void removeBook(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        if (book != null) {
            session.delete(book);
            LOG.info("Book successfully removed, book info: " + book);
        }

    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        LOG.info("Book successfully loaded, book info: " + book);
        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book> listBook = session.createQuery("from Book").list();

        for (Book book : listBook) {
            LOG.info("Book list: " + book);
        }

        return listBook;
    }
}
