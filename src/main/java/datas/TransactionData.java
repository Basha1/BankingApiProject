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
public class TransactionData {

    public static void main(String[] args) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("test-connection");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        //create customers
        Customer customer1 = new Customer();
        customer1.setName("Brian  O'Neil");
        customer1.setAddress("34A Hillview");
        customer1.setEmail("brian@gmail.com");
        customer1.setSecurity_credentials((int) Math.floor(Math.random() * 9999));

        Customer customer2 = new Customer();
        customer2.setName("Seamus Kennedy");
        customer2.setAddress("10 Station Rd Dromahair");
        customer2.setEmail("seamus@gmail.com");
        customer2.setSecurity_credentials((int) Math.floor(Math.random() * 9999));

        Customer customer3 = new Customer();
        customer3.setName("Martin  O'Kennedy");
        customer3.setAddress("5 Prosperous Naas");
        customer3.setEmail("martin@gmail.com");
        customer3.setSecurity_credentials((int) Math.floor(Math.random() * 9999));

        Customer customer4 = new Customer();
        customer4.setName("Eddie  Smith");
        customer4.setAddress("1 Carrolls Quay, Cork");
        customer4.setEmail("eddie@gmail.com");
        customer4.setSecurity_credentials((int) Math.floor(Math.random() * 9999));

        ///
        Account account1 = new Account();
        Account account2 = new Account();
        Account account3 = new Account();
        Account account4 = new Account();

        entitymanager.persist(customer1);
        entitymanager.persist(customer2);
        entitymanager.persist(customer3);
        entitymanager.persist(customer4);

        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        Transaction transaction3 = new Transaction();
        Transaction transaction4 = new Transaction();

        //create transactions
        transaction1.setType("Debit");
        transaction1.setDescription("Returned item Technology store PC World");
        transaction1.setPost_balance(150.99);
        transaction1.getDate();

        transaction2.setType("Debit");
        transaction2.setDescription("Family birthday");
        transaction2.setPost_balance(50.00);
        transaction2.getDate();

        transaction3.setType("Credit");
        transaction3.setDescription("Cinema");
        transaction3.setPost_balance(24.75);
        transaction3.getDate();

        transaction4.setType("Credit");
        transaction4.setDescription("Netflix: monthly charge");
        transaction4.setPost_balance(11.99);
        transaction4.getDate();

        //setting transactions to customer and accounts
        transaction1.setUser(customer1);
        transaction1.setAccount(account1);
        //
        transaction2.setUser(customer2);
        transaction2.setAccount(account2);
        //
        transaction3.setUser(customer3);
        transaction3.setAccount(account3);
        //
        transaction4.setUser(customer4);
        transaction4.setAccount(account4);

        ArrayList<Transaction> list = new ArrayList<>();
        list.add(transaction1);
        list.add(transaction2);
        list.add(transaction3);
        list.add(transaction4);

        //
        customer1.setTransactions(list);
        entitymanager.persist(customer1);
        //
        customer2.setTransactions(list);
        entitymanager.persist(customer2);
        //
        customer3.setTransactions(list);
        entitymanager.persist(customer3);
        //
        customer4.setTransactions(list);
        entitymanager.persist(customer4);

        entitymanager.getTransaction().commit();

        Customer test = entitymanager.find(Customer.class, 2);

        for (Transaction transaction : test.getTransactions()) {
            System.out.println(transaction);

        }
        entitymanager.close();
        emfactory.close();

    }

}
