/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author x15368301
 */
@Entity
@Table
@XmlRootElement
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int account_no;
    private int sort_code;
    private double balance;

    public Account() {
        this.account_no = (int) Math.floor(Math.random() * 100);
        this.sort_code = (int) Math.floor(Math.random() * 50);
        this.balance = (double) Math.floor(Math.random() * 1000);
    }

    public Account(int account_no, int sort_code, double balance) {
        this.account_no = account_no;
        this.sort_code = sort_code;
        this.balance = balance;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public int getAccount_no() {
        return account_no;
    }

    /**
     *
     * @return
     */
    public int getSort_code() {
        return sort_code;
    }

    /**
     *
     * @return
     */
    public double getBalance() {
        return balance;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param account_no
     */
    public void setAccount_no(int account_no) {
        this.account_no = account_no;
    }

    /**
     *
     * @param sort_code
     */
    public void setSort_code(int sort_code) {
        this.sort_code = sort_code;
    }

    /**
     *
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer user;
    
    @XmlTransient
    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", user=" + user.getName() + '}';
    }

    
}
