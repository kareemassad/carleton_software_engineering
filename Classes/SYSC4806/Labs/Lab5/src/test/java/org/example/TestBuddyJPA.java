package org.example;

import jakarta.persistence.*;
import org.example.model.AddressBook;
import org.example.model.BuddyInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestBuddyJPA {
    private TestBuddyJPA testBuddyJPA;

    @Before
    public void setUp() {
        testBuddyJPA = new TestBuddyJPA();
    }

    @Test
    public void testPerformJPA(){
        BuddyInfo buddy1 = new BuddyInfo();
        BuddyInfo buddy2 = new BuddyInfo("kareem", "111111111");
        AddressBook ab = new AddressBook();
        ab.addBuddyInfo(buddy1);
        ab.addBuddyInfo(buddy2);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");

        EntityManager em = emf.createEntityManager();

        // Creating a new transaction
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Persisting the product entity objects
        em.persist(buddy1);
        em.persist(buddy2);
        em.persist(ab);

        tx.commit();

        // Querying the contents of the database using JPQL query
        Query q = em.createQuery("SELECT p FROM BuddyInfo p");

        @SuppressWarnings("unchecked")
        List<BuddyInfo> results = q.getResultList();

        System.out.println("List of products\n----------------");

        for (BuddyInfo b : results) {

            System.out.println(b.getName() + " (id=" + b.getId() + ")");
        }

        System.out.println("///////");

        Query q2 = em.createQuery("SELECT q from AddressBook q");
        List<AddressBook> res = q2.getResultList();
        for (AddressBook ab1 : res) {
            for (BuddyInfo b : ab1.getBuddies()) {
                System.out.println(b.getName() + " (id=" + b.getId() + ")");
            }
        }


        // Closing connection
        em.close();

        emf.close();

    }

    public static void main(String[] args) {
        TestBuddyJPA testBuddyJPA = new TestBuddyJPA();
        testBuddyJPA.testPerformJPA();
    }

}
