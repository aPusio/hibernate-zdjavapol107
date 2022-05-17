package org.example;

import org.example.model.Author;
import org.example.model.Car;
import org.example.model.Movie;
import org.example.model.Wheel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class App
{
    public static void main( String[] args ) {
//        Car fiatPanda = new Car("Fiat Panda", 2000, 3, 4);
//        Car build = Car.builder().amountOfDoors(2).build();
//        Wheel wheel = new Wheel();
        Movie movie = new Movie();
        movie.setTitle("Titanic");
        movie.setReleaseDate(LocalDate.now());

        Author author = new Author();
        author.setFirstName("Adam");
        author.setLastName("Adamski");
        author.setAddress("Gdansk");

        Author all = new Author(666L, "AAA", "BBB", "CCC");

        SessionFactory sessionFactory = new HibernateFactory().getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //INSERT INTO MOVIE ...
        session.save(movie);
        session.save(author);
        session.save(all);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
