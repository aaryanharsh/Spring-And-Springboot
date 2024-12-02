package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {

			//createCourseAndStudents(appDAO);

			//findCourseAndStudents(appDAO);

			//findStudentAndCourses(appDAO);

			//addMoreCoursesForStudent(appDAO);

			//deleteCourse(appDAO);

			deleteStudent(appDAO);

		};
	}

	private void deleteStudent(AppDAO appDAO) {

		int theId = 1;

		System.out.println("deleting student id: " + theId);
		appDAO.deleteStudentById(theId);

		System.out.println("Student deleted, Bazzinga!!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {

		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		//create more courses
		Course tempCourse1 = new Course("Rubic's cube - How to speed Up!");
		Course tempCourse2 = new Course("Atari 2600 - Game Dvelopment");

		//add courses to student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Saving student: " + tempStudent);
		System.out.println("associated courses: " + tempStudent.getCourses());

		appDAO.update(tempStudent);

		System.out.println("More courses added for student, Bazzinga!!");
	}

	private void findStudentAndCourses(AppDAO appDAO) {

		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		System.out.println("Loaded Student: "+ tempStudent);
		System.out.println("Associated courses: "+ tempStudent.getCourses());

		System.out.println("Associated Courses retrieved by Student, bazzinga!!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {

		int theId = 10;
		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

		System.out.println("Loaded Course: " + tempCourse);

		System.out.println("Assoiated Students: "+ tempCourse.getStudents());

		System.out.println("Bazzinga Done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {

		//create a course
		Course tempCourse = new Course("Pacman - How to score one million Points!");

		//create the students
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

		//add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		//save the course and associated students
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("associated students: " + tempCourse.getStudents());

		appDAO.save(tempCourse);

		System.out.println("Course And Students Added, bazzinga!!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int theId = 10;
		System.out.println("Deleting course id: " + theId);
		appDAO.deleteCourseById(theId);

		System.out.println("Deleted course and associated reviews due to cascadetype.ALL, bazzinga!!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println(tempCourse);

		// print the associated reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Course and Reviews retrieved, bazzinga!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		//create the course
		Course tempCourse = new Course("Pacman - How To Score One Million Points!");

		//add some reviews
		tempCourse.addReview(new Review("Great Course...Loved it!!"));
		tempCourse.addReview(new Review("Cool course, Job Well Done!"));
		tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

		//save the course ... and leverage the cascade All
		System.out.println("saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done, creating course and adding reviews Bazzinga!");

	}

	private void deleteCourse(AppDAO appDAO) {

		int theId = 10;

		System.out.println("deleting course id: "+ theId);

		appDAO.deleteCourseById(theId);

		System.out.println("deleted course, bazzinga gringo!!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;

		//find the course id
		System.out.println("Finding course id: "+ theId);
		Course tempCourse = appDAO.findCourseById(theId);

		//update the course by setter method
		System.out.println("Updating course id: "+ theId);
		tempCourse.setTitle("Enjoy The Simple Things");

		//update info in Database using appDAO
		appDAO.update(tempCourse);

		System.out.println("Course Updated, Bazzinga!!");
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;

		//find the instructor
		System.out.println("Finding instructor id: "+ theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		//update the instructor
		System.out.println("Updating instructor id: "+ theId);
		tempInstructor.setLastName("TESTER");

		appDAO.update(tempInstructor);
		System.out.println("Instructor Updated, Bazzinga!");

	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId = 1;

		//find the instructor
		System.out.println("Finding instructor id: "+ theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: "+ tempInstructor.getCourses());

		System.out.println("Done, bazzinga!!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int theId = 1;
		//find instructor
		System.out.println("Finding Instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: "+ tempInstructor);

		//finding courses for instructor
		System.out.println("Finding courses for instructor id: "+theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		//associate the objects
		tempInstructor.setCourses(courses);

		System.out.println("The associated courses: " + tempInstructor.getCourses());

		System.out.println("Done, bazzinga!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId = 1;
		System.out.println("finding Instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: "+ tempInstructor);
		System.out.println("the associated courses: "+ tempInstructor.getCourses());
		System.out.println("Done, Bazzinga!");
	}

	private void createInstructorWithCourses(AppDAO appDAO)
	{
		//create the instructor
		Instructor tempInstructor = new Instructor("Susan", "Public", "susan@luv2code.com");

		//create Instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.youtube.com",
						"Video Games, Bazzinga!!!");

		//associate the Objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		//add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		//save the instructor by making a call to appDAO.save
		//
		//NOTE: This will also SAVE the courses
		//because of CascadeType.PERSIST

		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The Course: " + tempInstructor.getCourses());

		appDAO.save(tempInstructor);

		System.out.println("Done Bazzinga!!");
	}


	private void deleteInstructorDetail(AppDAO appDAO)
	{
		int theId = 9;
		System.out.println("Deleting instructor detail id: " + theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Bazzinga deleted gringo!!");
	}

	private void findInstructorDetail(AppDAO appDAO)
	{

		// get the instructor detail
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		//print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("Bazzinga Done!!");
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
		Instructor tempInstructor = new Instructor("madhu", "patel", "patel@luv2code.com");

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










