package com.javacurse.ce.JavaJunior.Seminar.lesson4.hw;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.List;

public class Homework {

    /**
     * 1. Создать сущность Student с полями:
     * 1.1 id - int
     * 1.2 firstName - string
     * 1.3 secondName - string
     * 1.4 age - int
     * 2. Подключить hibernate. Реализовать простые запросы: Find(by id), Persist, Merge, Remove
     * 3. Попробовать написать запрос поиска всех студентов старше 20 лет (session.createQuery)
     */
    public static void main(String[] args) {
        Configuration configure = new Configuration().configure();
        try (SessionFactory sessionFactory = configure.buildSessionFactory()) {
            insertStudent(sessionFactory);
            getStudentsFromDatabase(sessionFactory);
            System.out.println(FindById (sessionFactory,3L));



        }


    }
    //Persist
    public static void insertStudent(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student e1 = new Student(1L, "Евгений", "Соловьев", 35L);
            session.persist(e1);
            Student B2 = new Student(2L, "Борис", "Борисов", 26L);
            session.persist(B2);
            Student B3 = new Student(3L, "Сергей", "Сергеев", 28L);
            session.persist(B3);
            Student B4 = new Student(4L, "Нина", "Ниновна", 24L);
            session.persist(B4);
            Student B5 = new Student(5L, "Катя", "Екатериновна", 23L);
            session.persist(B5);
            Student B6 = new Student(6L, "Гоша", "ПростоГоша", 71L);
            session.persist(B6);


            transaction.commit();


        }
    }
    public static void getStudentsFromDatabase(SessionFactory sessionFactory) {
        List<Student> students = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            students = session.createQuery("FROM Student", Student.class).getResultList();
            transaction.commit();
            for (Student student : students) {
                System.out.println("id = " + student.getId() + ", firstName = " + student.getFirstName() +
                        ", secondName = " + student.getSecondName() + ", age = " + student.getAge());
            }
        }
    }

   // Find(by id)
    public static Student FindById (SessionFactory sessionFactory,Long id){
        Student student = null;
        try(Session session = sessionFactory.openSession()) {
            student = session.get(Student.class, id);
            Transaction transaction = session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

}












