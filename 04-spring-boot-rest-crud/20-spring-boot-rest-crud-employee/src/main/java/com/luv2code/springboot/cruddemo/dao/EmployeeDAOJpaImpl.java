package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{
    //define field for Entity manager
    private EntityManager entityManager;


    //setup constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }
    @Override
    public List<Employee> findAll() {
        //create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
        //execute query and get result list
        List<Employee> employees = theQuery.getResultList();
        //return the result
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        //get employee
        Employee theEmployee = entityManager.find(Employee.class, theId);

        //return employee
        return theEmployee;
    }
    //we don't @Transactional as DAO layer, It will be handled by Service Layer
    //remember we are making use of DAOs and Services together, so the best practice is DAO doesn't manage Transactional
    // instead better managed at Service layer
    @Override
    public Employee save(Employee theEmployee) {
        //save employee || merge : if(id == 0) then insert/save else update
        Employee dbEmployee = entityManager.merge(theEmployee);

        //return dbEmployee
        return dbEmployee;  //dbEmployee has updated id from the database in case of insert
    }

    @Override
    public void deleteById(int theId) {
        //find employee by id
        Employee theEmployee = entityManager.find(Employee.class, theId);


        //remove employee
        entityManager.remove(theEmployee);
    }
}
