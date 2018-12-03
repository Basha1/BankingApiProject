/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datas;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Account;
import models.Customer;
import models.Transaction;

/**
 *
 * @author x15368301
 */
public class AccountData {

    public static void main(String[] args) {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("test-connection");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        Customer customer1 = new Customer();
        customer1.setName("Jack Bent");
        customer1.setAddress("25 Lr Strand Kanturk");
        customer1.setEmail("jack@gmail.com");
        customer1.setSecurity_credentials((int) Math.floor(Math.random() * 9999));

        Customer customer2 = new Customer();
        customer2.setName("Ana Willian");
        customer2.setAddress("10 Parkview House");
        customer2.setEmail("ana.w@gmail.com");
        customer2.setSecurity_credentials((int) Math.floor(Math.random() * 9999));

        Customer customer3 = new Customer();
        customer3.setName("Michael  Kennedy");
        customer3.setAddress("52 Clover Hill");
        customer3.setEmail("michael@gmail.com");
        customer3.setSecurity_credentials((int) Math.floor(Math.random() * 9999));

        Customer customer4 = new Customer();
        customer4.setName("Gareth  Smith");
        customer4.setAddress("101 South Main st Wexford");
        customer4.setEmail("gareth@gmail.com");
        customer4.setSecurity_credentials((int) Math.floor(Math.random() * 9999));

        entitymanager.persist(customer1);
        entitymanager.persist(customer2);
        entitymanager.persist(customer3);
        entitymanager.persist(customer4);

        Account account1 = new Account();
        Account account2 = new Account();
        Account account3 = new Account();
        Account account4 = new Account();

        account1.setUser(customer1);
        account1.setUser(customer2);
        account1.setUser(customer3);
        account1.setUser(customer4);

        ArrayList<Account> list = new ArrayList<>();
        list.add(account1);
        list.add(account2);
        list.add(account3);
        list.add(account4);
        
        entitymanager.getTransaction().commit();
        
        Customer test = entitymanager.find(Customer.class,1);
        
        
        for(Account account : test.getAccounts()){
            System.out.println(account);
        }
        entitymanager.close();
        emfactory.close();
    }
}
