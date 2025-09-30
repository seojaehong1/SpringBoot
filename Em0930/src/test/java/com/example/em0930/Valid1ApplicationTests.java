package com.example.em0930;

import com.example.em0930.entity.MyUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Valid1ApplicationTests {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void contextLoads() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        MyUser myUser = MyUser.builder()
                .name("서재홍홍2")
                .password("12345678")
                .email("wtme3@gmail.com")
                .build();

        em.persist(myUser);

        myUser.setName("테스트");

        em.flush();
        transaction.commit();

        em.close();
    }

    @Test
    void template() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();


        em.flush();
        transaction.commit();

        em.close();
    }

    @Test
    void find() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        
        MyUser myUser = em.find(MyUser.class, 1L);
        myUser.setName("수정된 1번 서재홍");
        System.out.println(myUser);


        em.flush();
        transaction.commit();

        em.close();
    }

    @Test
    void merge() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        MyUser myUser = MyUser.builder()
                .id(1L)
                .name("관리자")
                .password("0000000")
                .email("admin@gmail.com")
                .build();

        em.merge(myUser);

        em.flush();
        transaction.commit();

        em.close();
    }


    @Test
    void testRemove() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        MyUser myUser = em.find(MyUser.class, 5l);

        em.remove(myUser);

        em.flush();
        transaction.commit();

        em.close();
    }

    @Test
    void findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        List<MyUser> list = em.createQuery("SELECT e FROM MyUser e", MyUser.class).getResultList();
        for(MyUser myUser : list) {
            System.out.println(myUser);
        }

        em.flush();
        transaction.commit();
        em.close();

    }



}
