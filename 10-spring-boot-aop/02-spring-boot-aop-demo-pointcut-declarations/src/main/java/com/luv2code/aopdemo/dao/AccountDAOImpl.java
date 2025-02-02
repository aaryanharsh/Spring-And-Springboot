package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;

    private String serviceCode;

    @Override
    public List<Account> findAccounts() {
       return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        //for academic ...simulate an exception
        if(tripWire){
            throw new RuntimeException("No Soup for u!!!");
        }
        List<Account> myAccounts = new ArrayList<>();

        //create sample accounts
        Account temp1 = new Account("john", "silver");
        Account temp2 = new Account("Madhu", "platinum");
        Account temp3 = new Account("Luca", "gold");

        //add them to our accounts list
        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);

        return myAccounts;
    }

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {

        System.out.println(getClass() + ": doWork()");
        return false;
    }

    public String getName() {

        System.out.println(getClass() + ": in getName()");
        return name;
    }

    public void setName(String name) {

        System.out.println(getClass() + ": in setName()");
        this.name = name;
    }

    public String getServiceCode() {

        System.out.println(getClass() + ": in getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {

        System.out.println(getClass() + ": in setServiceCode()");
        this.serviceCode = serviceCode;
    }

}
