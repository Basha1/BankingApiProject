/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Customer;

/**
 *
 * @author x15368301
 */
public class CustomerData {

    public static void main(String[] args) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("test-connection");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        Customer customerOne = new Customer();
        customerOne.setName("Eric  Walsh");
        customerOne.setAddress("25 Longford Pass Thurles");
        customerOne.setEmail("eric@gmail.com");
        customerOne.setSecurity_credentials((int) Math.floor(Math.random() * 9999));

        Customer customerTwo = new Customer();
        customerTwo.setName("Mick  Murray");
        customerTwo.setAddress("10 Parkview House");
        customerTwo.setEmail("mick@gmail.com");
        customerTwo.setSecurity_credentials((int) Math.floor(Math.random() * 9999));

        Customer customerThree = new Customer();
        customerThree.setName("Gary  Murray");
        customerThree.setAddress("52 Fahamore Castlegregory");
        customerThree.setEmail("Murray@gmail.com");
        customerThree.setSecurity_credentials((int) Math.floor(Math.random() * 9999));

        Customer customerFour = new Customer();
        customerFour.setName("Terry  O'Smith");
        customerFour.setAddress("101 Church st Ennistymon Ennis");
        customerFour.setEmail("terry@gmail.com");
        customerFour.setSecurity_credentials((int) Math.floor(Math.random() * 9999));

        entitymanager.persist(customerOne);
        entitymanager.persist(customerTwo);
        entitymanager.persist(customerThree);
        entitymanager.persist(customerFour);
        
        entitymanager.getTransaction().commit();
        
        System.out.println(customerOne);
        System.out.println(customerTwo);
        System.out.println(customerThree);
        System.out.println(customerFour);
        
        entitymanager.close();
        emfactory.close();
    }

}
