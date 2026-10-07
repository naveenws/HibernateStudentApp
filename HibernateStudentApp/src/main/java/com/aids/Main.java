package com.aids;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil
                .getSessionFactory()
                .openSession();

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            Student student = new Student(
                    102,
                    "Naveen",
                    "naveen@gmail.com",
                    "Data Science"
            );

            session.persist(student);

            transaction.commit();

            System.out.println("Student inserted successfully!");

            transaction = session.beginTransaction();

            Student existingStudent = session.get(Student.class, 102);

            existingStudent.setCourse(
                    "Artificial Intelligence and Data Science"
            );

            transaction.commit();

            System.out.println("Student updated successfully!");

            Student updatedStudent = session.get(Student.class, 102);

            System.out.println("\nStudent Details:");
            System.out.println("ID     : " + updatedStudent.getId());
            System.out.println("Name   : " + updatedStudent.getName());
            System.out.println("Email  : " + updatedStudent.getEmail());
            System.out.println("Course : " + updatedStudent.getCourse());

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {

            session.close();
        }
    }
}