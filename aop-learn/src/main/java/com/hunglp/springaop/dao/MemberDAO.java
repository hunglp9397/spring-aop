package com.hunglp.springaop.dao;


import org.springframework.stereotype.Component;

@Component
public class MemberDAO {

    public void addAccount(){
        System.out.println( getClass() + " : Adding a Membership ");
    }
}
