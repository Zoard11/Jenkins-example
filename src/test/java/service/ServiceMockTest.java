package service;


import domain.Grade;
import domain.Homework;
import domain.Pair;
import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

public class ServiceMockTest {


    Service service;

    @Mock
    StudentXMLRepository studentRepository;
    @Mock
    HomeworkXMLRepository homeworkRepository ;
    @Mock
    GradeXMLRepository gradeRepository;





    @BeforeEach
    void setUp() {
        studentRepository=Mockito.mock((StudentXMLRepository.class));
        homeworkRepository=Mockito.mock((HomeworkXMLRepository.class));
        gradeRepository=Mockito.mock(GradeXMLRepository.class);

        service = new Service(studentRepository, homeworkRepository, gradeRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveStudent() {


//        Mockito.doReturn(1).when(studentRepository).save(new Student(anyString(),anyString(),anyInt()));
//        Mockito.doReturn(new Student("1","Zoard",533)).when(studentRepository).save(new Student("1","Zoard",533));
        Mockito.doReturn(null).when(studentRepository).save(new Student("1","Zoard",533));
        assertEquals(0,service.saveStudent( "1", "Zoard", 533));

        Mockito.verify(studentRepository,Mockito.times(1)).save(new Student("1","Zoard",533));

    }

    @Test
    void deleteStudent() {
        Mockito.doReturn(new Student("1","Zoard",533)).when(studentRepository).delete(anyString());
        assertEquals(0,service.deleteStudent("1"));
    }


    @Test
    void saveGrade() {

        Mockito.doReturn(new Student("1","Zoard",533)).when(studentRepository).findOne("1");
        Mockito.doReturn(new Homework("2","valami",14,7)).when(homeworkRepository).findOne("2");

        Mockito.doReturn(14).when(homeworkRepository).findOne("2").getDeadline();

        Grade grade = new Grade(new Pair("1", "2"), 7.6, 11, "3 answer was wrong.");
        Mockito.doReturn(1).when(gradeRepository).save(grade);


        assertNotEquals(0,service.saveGrade( "1", "2", 7.6,11,"3 answer was wrong."));
    }

    @Test
    void saveHomework() {
        Mockito.doReturn(new Homework("13", "This is a description.", 8,5)).when(homeworkRepository).save(new Homework("13", "This is a description.", 8,5));
        assertNotEquals(0,service.saveHomework( "13", "This is a description.", 8,5));
    }

}
