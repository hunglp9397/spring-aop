package com.hunglp.springaop.aspect;


import com.hunglp.springaop.dao.AccountDAO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(public void add*())")            // Match any method start with "add"
    //@Before("execution(* add*())")                    // Add any method start with "add" and any return type
    // Parameter Pattern Wildcards:
    // () : matches a method with no arguments
    // (*) : matches a method with one argument of any type
    // (..) : matches a method with 0 or more argument of any type

    public void beforeAddAccount(){
        System.out.println("Executing @Before advice on addAccount()");


    }






    // Point cut for all method in package DAO
    @Pointcut("execution(* com.hunglp.springaop.dao.*.*(..))")
    public void forDaoPackage() {
        System.out.println("Execution @Pointcut for all method in DAO package, This will call performAPI first ");
    }

    // Pointcut for getter method
    @Pointcut("execution(* com.hunglp.springaop.dao.*.get*(..))")
    public void forGetterMethod(){

    }

    // Pointcut for setter method
    @Pointcut("execution(* com.hunglp.springaop.dao.*.set*(..))")
    public void forSetterMethod(){

    }

    // Pointcut for all method in package.. exclude getter/setter method
    @Pointcut("forDaoPackage() && !(forGetterMethod() || forSetterMethod())")
    private void forDaoPackageNoGetterSetter(){

    }

    @Before("forDaoPackage()")
    public void performAPI(JoinPoint joinPoint) {
        System.out.println("Performing API");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Object[] args = joinPoint.getArgs();
        System.out.println("Method argument : ");
        for(Object arg : args){
            System.out.println(arg);
        }


        System.out.println("Method signature : " + joinPoint);
    }




}
