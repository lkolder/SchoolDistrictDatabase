// --== CS400 File Header Information ==--
// Name: Luke Kolder
// Email: lkolder@wisc.edu
// Team: NG
// TA: Daniel
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class TestEngineer {

    private static PersonAtSchool testItem1 = new PersonAtSchool('S', "Tom", "Smith", 18, 12, 'H');
    private static PersonAtSchool testItem2 = new PersonAtSchool('S', "Jake", "Sharp", 14, 7, 'M');
    private static PersonAtSchool testItem3 = new PersonAtSchool('S', "Adam", "Gibson", 10, 4, 'E');
    private static PersonAtSchool testItem4 = new PersonAtSchool('F', "Scott", "Strong", 40, 10, "MATH", 'H');
    private static PersonAtSchool testItem5 = new PersonAtSchool('F', "Kim", "Duffy", 34, 6, "HISTORY", 'M');
    private static PersonAtSchool testItem6 = new PersonAtSchool('F', "Sarah", "Lenihan", 50, 3, "ENGLISH", 'E');

    private static BackEndDeveloper testBed = new BackEndDeveloper();

    private static void fillTestBed() {
        testBed.clearFaculty();
        testBed.clearStudents();
        testBed.addStudent((char)testItem1.getFacultyOrStudent(), testItem1.getFirstName().toString(), 
            testItem1.getLastName().toString(), (int)testItem1.getAge(), (int)testItem1.getGrade(), (char)testItem1.getSchool());
        testBed.addFaculty((char)testItem4.getFacultyOrStudent(), testItem4.getFirstName().toString(), testItem4.getLastName().toString(), 
            (int)testItem4.getAge(), (int)testItem4.getGrade(), testItem4.getSubject().toString(), (char)testItem4.getSchool());
        testBed.addStudent((char)testItem2.getFacultyOrStudent(), testItem2.getFirstName().toString(), 
            testItem2.getLastName().toString(), (int)testItem2.getAge(), (int)testItem2.getGrade(), (char)testItem2.getSchool());
        testBed.addFaculty((char)testItem5.getFacultyOrStudent(), testItem5.getFirstName().toString(), testItem5.getLastName().toString(), 
            (int)testItem5.getAge(), (int)testItem5.getGrade(), testItem5.getSubject().toString(), (char)testItem5.getSchool());
        testBed.addStudent((char)testItem3.getFacultyOrStudent(), testItem3.getFirstName().toString(), 
            testItem3.getLastName().toString(), (int)testItem3.getAge(), (int)testItem3.getGrade(), (char)testItem3.getSchool());
        testBed.addFaculty((char)testItem6.getFacultyOrStudent(), testItem6.getFirstName().toString(), testItem6.getLastName().toString(), 
            (int)testItem6.getAge(), (int)testItem6.getGrade(), testItem6.getSubject().toString(), (char)testItem6.getSchool());
    }

    /**
     * This method tests to make sure that a new member can be added to the school
     * database. If either a faculty or student cannot be added successfully, an
     * error message will be printed.
     */
    @Test
    void testAdd() {
      try {
        testBed.addStudent((char)testItem1.getFacultyOrStudent(), testItem1.getFirstName().toString(), 
            testItem1.getLastName().toString(), (int)testItem1.getAge(), (int)testItem1.getGrade(), (char)testItem1.getSchool());
        testBed.addStudent((char)testItem2.getFacultyOrStudent(), testItem2.getFirstName().toString(), 
            testItem2.getLastName().toString(), (int)testItem2.getAge(), (int)testItem2.getGrade(), (char)testItem2.getSchool());
      }
      catch(IllegalArgumentException e) {
        fail("Adding a new faculty to the school database was unsuccesful.");
      }
    }

    /**
     * This test calls the list method on the school database and compares the
     * returned value to the expected result. If unsuccessful, an error message will
     * be printed.
     */
    @Test
    void testList() {
        fillTestBed();
        PersonAtSchool[] testArr1 = new PersonAtSchool[] { testItem2, testItem3, testItem1 };
        PersonAtSchool[] testArr2 = new PersonAtSchool[] { testItem6, testItem5, testItem4 };
        PersonAtSchool[] compArr1 = testBed.listStudents();
        PersonAtSchool[] compArr2 = testBed.listFaculty();
        for(int i = 0; i < testArr1.length; i++) {
          if(!testArr1[i].getLastName().equals(compArr1[i].getLastName())) {
            fail("The list students method did not return the expected result.");
          }
        }
        for(int i = 0; i < testArr2.length; i++) {
          if(!testArr2[i].getLastName().equals(compArr2[i].getLastName())) {
            fail("The list faculty method did not return the expected result.");
          }
        }

    }

    /**
     * This method tests to ensure that the search method functions as expected. If
     * unsuccessful, an error message will be printed.
     */
    @Test
    void testSearch() {
        fillTestBed();
        if (!testBed.searchFaculty("Strong").getLastName().equals(testItem4.getLastName())) {
            fail("The search faculty method did not function as expected.");
        }

        if (!testBed.searchStudent("Smith").getLastName().equals(testItem1.getLastName())) {
            fail("The search students method did not function as expected.");
        }

    }

    /**
     * This method tests to ensure that the clear method functions as expected. If
     * unsuccessful, an error message will be printed.
     */
    @Test
    void testClear() {
        fillTestBed();
        testBed.clearStudents();
        if (testBed.listStudents().length != 0) {
            fail("All students were not cleared from the school database when the clear method was run.");
        }
        testBed.clearFaculty();
        if (testBed.listFaculty().length != 0) {
            fail("All faculty were not cleared from the school database when the clear method was run.");
        }
    }

    /**
     * This method tests to see how the file reader handles both properly and
     * improperly formatted inputs. If either are not handled correctly, an error
     * message will be printed.
     */
    @Test
    void testFileReader() {
        FileReader reader = new FileReader();

        try {
            reader.readFile("ImproperFormat.txt");

        } catch (Exception e) {
          try {
            reader.readFile("ProperFormat.txt");
        } catch (Exception f) {
          fail("The file reader did not succesfully handle the properly formatted text.");
        }
        }

        

    }

}
