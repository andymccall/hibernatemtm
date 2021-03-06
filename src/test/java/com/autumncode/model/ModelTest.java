package com.autumncode.model;


import com.autumncode.db.SessionManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

public class ModelTest {
    @Test
    public void animalBarnTest() {
        Session session = SessionManager.openSession();
        Transaction tx = session.beginTransaction();
        Barn barn = new Barn();
        barn.setName("the barn");
        barn.setAddress("1234 main");

        String[] names = new String[]{"foo", "bar", "baz", "quux"};
        for (String name : names) {
            Animal animal = new Animal();
            animal.setName(name);
            animal.setBreed("Dog");
            animal.setBarn(barn);
            session.save(animal);
        }
        session.save(barn);

        tx.commit();
        session.close();

        session = SessionManager.openSession();
        tx = session.beginTransaction();
        Query<Barn> query = session.createQuery("from Barn b", Barn.class);
        for (Object o : query.list()) {
            System.out.println(o);
        }
        tx.commit();
        session.close();

    }
}
