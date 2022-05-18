package org.example;

import org.example.dao.AuthorDao;
import org.example.dao.MovieDao;
import org.example.model.Author;
import org.example.model.Car;
import org.example.model.Movie;
import org.example.model.Wheel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new HibernateFactory().getSessionFactory();
        AuthorDao authorDao = new AuthorDao(sessionFactory);
        MovieDao movieDao = new MovieDao(sessionFactory);

        saveAuthorExample(authorDao);
        getByIdWithOptional(movieDao);
        updateExample(movieDao);
        deleteExample(authorDao);

        sessionFactory.close();
    }

    private static void deleteExample(AuthorDao authorDao) {
        Long id = authorDao.save(new Author("Leszek", "Leszkowski", "Wejcherowo"));
        authorDao.delete(id);
        System.out.println("author with id " + id + " was deleted");
    }

    private static void updateExample(MovieDao movieDao) {
        Optional<Movie> byId = movieDao.getById(1L);
        if (byId.isPresent()) {
            Movie movie = byId.get();
            System.out.println("Movie before update:" + movie);
            movie.setTitle("Updated Title");
            movieDao.update(movie);
//            System.out.println("Movie after update: " + movie);
        }
        Optional<Movie> updatedMovie = movieDao.getById(1L);
        updatedMovie.ifPresent(movie -> System.out.println("Movie after update: " + movie));
    }

    private static void getByIdWithOptional(MovieDao movieDao) {
        //get movie by id
        movieDao.save(new Movie("Swinka Peppa", LocalDate.now()));
        Optional<Movie> optionalMovie = movieDao.getById(99L);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            System.out.println(movie);
        }

        //wyjatek bez sprawdzenia isPresent
//        String title = optionalMovie.get().getTitle();

        //or else czyli wartosc default
        Movie movie = movieDao.getById(99L)
                .orElse(new Movie("FILM NIE ZNALEZIONY", LocalDate.MAX));

        optionalMovie.ifPresent(movie2 -> {
            System.out.println("ZNALEZIONO ! 🤣🤣🤣🤣");
            System.out.println(movie2);
        });
    }

    private static void saveAuthorExample(AuthorDao authorDao) {
        //save example 1
        Author tomek = new Author();
        tomek.setFirstName("Tomek");
        tomek.setLastName("Tomczyk");
        tomek.setAddress("Gdansk");
        authorDao.save(tomek);

        //save example 2
        authorDao.save(new Author("Maciej", "Maciejewski", "Gdynia"));

        //save example 3
        authorDao.save(Author.builder()
                .firstName("Robert")
                .lastName("Robertowski")
                .address("Sopot")
                .build());
    }
}
