package org.example;

import org.example.model.Car;
import org.example.model.Wheel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App
{
    public static void main( String[] args ) {
//        Car fiatPanda = new Car("Fiat Panda", 2000, 3, 4);
//        Car build = Car.builder().amountOfDoors(2).build();
//        Wheel wheel = new Wheel();

        SessionFactory sessionFactory = new HibernateFactory().getSessionFactory();
        Session session = sessionFactory.openSession();
        session.close();
        sessionFactory.close();
    }
}
