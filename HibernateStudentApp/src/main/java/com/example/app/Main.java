
package com.example.app;

import com.example.entity.Student;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {

        try {
            // INSERT student
            try (Session session = HibernateUtil
                    .getSessionFactory().openSession()) {

                Transaction tx = session.beginTransaction();

                Student student = new Student(
                        101,
                        "Naveen",
                        "naveen@gmail.com",
                        "Artificial Intelligence"
                );

                session.persist(student);
                tx.commit();

                System.out.println("Student inserted successfully.");
            }

            // UPDATE student
            try (Session session = HibernateUtil
                    .getSessionFactory().openSession()) {

                Transaction tx = session.beginTransaction();

                Student student = session.get(Student.class, 101);

                if (student != null) {
                    student.setEmail("naveen@example.com");
                    student.setCourse("Data Science");
                }

                tx.commit();
                System.out.println("Student updated successfully.");
            }

            // VERIFY the saved record
            try (Session session = HibernateUtil
                    .getSessionFactory().openSession()) {

                Student student = session.get(Student.class, 101);

                System.out.println("Verified record:");
                System.out.println(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
