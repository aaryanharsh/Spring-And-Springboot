package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createStudent(studentDAO);

			createMultipleStudents(studentDAO);

			//readStudent(studentDAO);

			//queryForStudents(studentDAO);

			//queryForStudentsByLastName(studentDAO);

			//updateStudent(studentDAO);

			//deleteStudent(studentDAO);

			//deleteAllStudent(studentDAO);
		};
	}

	private void deleteAllStudent(StudentDAO studentDAO) {
		System.out.println("Deleting all students ");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count: "+ numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting the studentID: "+ studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		//retrieve the student by id: primarykey
		int studentId = 1;
		System.out.println("Getting Student with id: "+ studentId);
		Student myStudent = studentDAO.findById(studentId);


		//change first name to "Scooby
		System.out.println("Updating student");
		myStudent.setFirstName("John");

		//update the student
		studentDAO.update(myStudent);


		//display the updated student
		System.out.println("Updated Student: "+ myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		//get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Mary");


		//display list of students
		for(Student tempStudent: theStudents)
			System.out.println(tempStudent);
	}

	private void queryForStudents(StudentDAO studentDAO) {
		//get a list of students
		List<Student> theStudents = studentDAO.findAll();

		//display list of students
		for(Student tempStudent: theStudents)
			System.out.println(tempStudent);
	}

	private void readStudent(StudentDAO studentDAO) {

		//create a student object
		System.out.println("Creating a new student...");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

		//save the student
		System.out.println("saving the student...");
		studentDAO.save(tempStudent);

		//display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: "+ theId);

		//retrieve the student based on id: primary key
		System.out.println("retrieving the student with id: "+ theId);
		Student myStudent = studentDAO.findById(theId);


		//display student
		System.out.println("Found the student: "+ myStudent);

	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		//create multiple students
		System.out.println("Creating  3 new student obj...");
		Student tempStudent1 = new Student("Pauli", "Sen" , "pauli@luv2code.com");
		Student tempStudent2 = new Student("John", "Dexter" , "john@luv2code.com");
		Student tempStudent3 = new Student("Mary", "Banjo" , "mary@luv2code.com");

		//save the student objects
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {

		//create the student Object
		System.out.println("Creating new student obj...");
		Student tempStudent = new Student("Paul", "Doe" , "paul@luv2code.com");

		//save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		//display the id of the saved student
		System.out.println("Saved srtudent. Generated id: " + tempStudent.getId());
	}

}