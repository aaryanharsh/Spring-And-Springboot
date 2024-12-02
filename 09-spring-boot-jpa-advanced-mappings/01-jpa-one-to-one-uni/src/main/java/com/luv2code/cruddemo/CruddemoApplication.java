package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CruddemoApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			//createInstructor(appDAO);

			//findInstructor(appDAO);

			deleteInstructor(appDAO);
		};
	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting Instructor id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Deleted Bazzinga!!");
	}

	private void findInstructor(AppDAO appDAO) {

		int theId = 2;
		System.out.println("Finding Instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {
		/*
		//create the instructor
		Instructor tempInstructor = new Instructor("chad", "darby", "darby@luv2code.com");

		//create Instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Luv 2 code!!!");
						*/


		//create the instructor
		Instructor tempInstructor = new Instructor("harsh", "aryan", "aryan@luv2code.com");

		//create Instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Luv 2 code, Bazzinga!!!");

		//associate the Objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//save the instructor
		//
		//Note: this wil ALSO save the details object
		//bcoz of CASCADETYPE.ALL
		//

		System.out.println("Saving instructor: "+ tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Bazzinga Done!!!");

	}

}









