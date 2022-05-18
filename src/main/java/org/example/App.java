package org.example;

import org.example.dao.BadgeDao;
import org.example.dao.EntityDao;
import org.example.dao.MovieDao;
import org.example.dao.old.OldAuthorDao;
import org.example.dao.old.OldMovieDao;
import org.example.model.Author;
import org.example.model.Badge;
import org.example.model.Movie;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new HibernateFactory().getSessionFactory();
        OldAuthorDao oldAuthorDao = new OldAuthorDao(sessionFactory);
        OldMovieDao oldMovieDao = new OldMovieDao(sessionFactory);

        saveAuthorExample(oldAuthorDao);
        getByIdWithOptional(oldMovieDao);
        updateExample(oldMovieDao);
        deleteExample(oldAuthorDao);

        MovieDao movieDao = new MovieDao(sessionFactory);
        BadgeDao badgeDao = new BadgeDao(sessionFactory);

        Movie movie = new Movie("Trzy swinki", LocalDate.of(1992, 12, 12));
        Badge badge = new Badge();
        badge.setName("SUPER ODZNAKA, SUPER FILM");
        badge.setValue(3);

        movie.setBadge(badge);
//        badge.setMovie(movie);

        badgeDao.save(badge);
        movieDao.save(movie);

        sessionFactory.close();
    }

    private static void deleteExample(OldAuthorDao oldAuthorDao) {
        Long id = oldAuthorDao.save(new Author("Leszek", "Leszkowski", "Wejcherowo"));
        oldAuthorDao.delete(id);
        System.out.println("author with id " + id + " was deleted");
    }

    private static void updateExample(OldMovieDao oldMovieDao) {
        Optional<Movie> byId = oldMovieDao.getById(1L);
        if (byId.isPresent()) {
            Movie movie = byId.get();
            System.out.println("Movie before update:" + movie);
            movie.setTitle("Updated Title");
            oldMovieDao.update(movie);
//            System.out.println("Movie after update: " + movie);
        }
        Optional<Movie> updatedMovie = oldMovieDao.getById(1L);
        updatedMovie.ifPresent(movie -> System.out.println("Movie after update: " + movie));
    }

    private static void getByIdWithOptional(OldMovieDao oldMovieDao) {
        //get movie by id
        oldMovieDao.save(new Movie("Swinka Peppa", LocalDate.now()));
        Optional<Movie> optionalMovie = oldMovieDao.getById(99L);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            System.out.println(movie);
        }

        //wyjatek bez sprawdzenia isPresent
//        String title = optionalMovie.get().getTitle();

        //or else czyli wartosc default
        Movie movie = oldMovieDao.getById(99L)
                .orElse(new Movie("FILM NIE ZNALEZIONY", LocalDate.MAX));

        optionalMovie.ifPresent(movie2 -> {
            System.out.println("ZNALEZIONO ! 🤣🤣🤣🤣");
            System.out.println(movie2);
        });
    }

    private static void saveAuthorExample(OldAuthorDao oldAuthorDao) {
        //save example 1
        Author tomek = new Author();
        tomek.setFirstName("Tomek");
        tomek.setLastName("Tomczyk");
        tomek.setAddress("Gdansk");
        oldAuthorDao.save(tomek);

        //save example 2
        oldAuthorDao.save(new Author("Maciej", "Maciejewski", "Gdynia"));

        //save example 3
        oldAuthorDao.save(Author.builder()
                .firstName("Robert")
                .lastName("Robertowski")
                .address("Sopot")
                .build());
    }
}
