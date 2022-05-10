package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

class ServiceTest {

    Service service;
    @BeforeEach
    void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllStudents() {
        assertNotEquals(-1,service.findAllStudents());
    }

    @Test
    void findAllHomework() {
    }

    @Test
    void findAllGrades() {
    }

    @Test
    void saveStudent() {
        assertEquals(1,service.saveStudent( "1", "Zoard", 533));
    }

    @Test
    void saveHomework() {
        assertNotEquals(0,service.saveHomework( "13", "This is a description.", 8,5));
    }

    @Test
    void saveGrade() {
        assertNotEquals(0,service.saveGrade( "1", "2", 7.6,11,"3 answer was wrong."));
    }

    @Test
    void deleteStudent() {
        assertEquals(1,service.deleteStudent("1"));
    }

    @Test
    void saveUpdateDeleteStudent(){
        assertAll(()-> assertNotEquals(0,service.saveStudent( "1", "Zoard", 533)),
                ()-> assertNotEquals(0,service.updateStudent( "1", "Zoard", 531)),
                ()-> assertNotEquals(0,service.deleteStudent( "1"))
                );
    }

    @ParameterizedTest
    @ValueSource(ints={111,211,311,411,511,611,711,811,911,115,681})
    void saveStudents(int group){
        assertEquals(1,service.saveStudent( "2", "Zoard", group));
    }

    @ParameterizedTest
    @ValueSource(ints={1,2,3,4,5,6,7,8,9,10})
    void deleteStudents(int id){
        String idString = String.valueOf(id);
        assertEquals(1,service.deleteStudent(idString));
    }

    @Test
    void deleteHomework() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void updateHomework() {
    }

    @Test
    void extendDeadline() {
    }

    @Test
    void createStudentFile() {
    }
}