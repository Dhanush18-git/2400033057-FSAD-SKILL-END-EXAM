package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo {

    public static void main(String[] args) {

        // Build SessionFactory from hibernate.cfg.xml
        SessionFactory sf = new Configuration()
                .configure()
                .addAnnotatedClass(Payment.class)
                .buildSessionFactory();

        // Open Session and begin Transaction
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        // ----- I. INSERT record using persistent object -----
        Payment p = new Payment();
        p.setId(101);
        p.setName("Sanju");
        p.setDate("2026-05-02");
        p.setStatus("Paid");
        p.setAmount(5000);

        s.save(p); // persist the object
        System.out.println("Record inserted successfully.");

        // ----- II. DELETE record using HQL with named parameter -----
        Query q = s.createQuery("delete from Payment where id = :pid");
        q.setParameter("pid", 101); // named parameter
        q.executeUpdate();
        System.out.println("Record deleted successfully.");

        // Commit and close
        t.commit();
        s.close();
        sf.close();
    }
}
