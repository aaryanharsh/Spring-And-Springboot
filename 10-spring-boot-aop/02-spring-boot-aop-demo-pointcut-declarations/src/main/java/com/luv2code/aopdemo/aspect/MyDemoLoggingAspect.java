package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around ("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        // print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @Around on method: "+ method);

        //get begin timestamp
        long begin = System.currentTimeMillis();


        //execute the method
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        }
        catch (Exception exc){

            //log the exception
            System.out.println(exc.getMessage());

            //rethrow exception
            throw exc;
        }

        //get the end timestamp
        long end = System.currentTimeMillis();

        //compute duration and display it
        long duration = end-begin;
        System.out.println("\n=====>>>>Duration: " + duration/1000.0 + "seconds");
        return result;
    }

    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyAccountsAdvice(JoinPoint theJoinPoint){

        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @After (finally) on method: "+ method);


    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccounts(
            JoinPoint theJoinPoint, Throwable theExc)
    {

        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @AfterThrowing on method: "+ method);

        //log the exception
        System.out.println("\n====>>>> The Exception is: "+ theExc);
    }






    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @AfterReturning on method: "+ method);

        //print out the results of the method call
        System.out.println("\n====>>>> result is: "+ result);

        //let's post-process the data .. let's modify it before it
        //makes it back to calling program

        //convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n====>>>> result is: "+ result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        //loop through account
        //get uppercase version of name
        //update the name on the account
        for(Account tempAccount: result) {
            String theUpperName = tempAccount.getName().toUpperCase();
            tempAccount.setName(theUpperName);
        }

    }


    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDAOPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n=======>>> Executing @Before advice on addAccount() method");

        //display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        //display method arguments passed in

        //get args
        Object[] args= theJoinPoint.getArgs();

        //loop thru args
        for(Object tempArg: args){
            System.out.println(tempArg);

            if(tempArg instanceof Account){
                //downcast and print Account specific stuff

                Account theAccount = (Account) tempArg;

                System.out.println("AccountName: "+ theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }

    }

}
