package haga_talga.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Course {
    public String ID;
    public String name;
    public int year;
    public int semester;
    public ArrayList<String> Students;

    public Course() {
        this.Students = new ArrayList<>();
    }

    public Course(String ID, String name, int year, int semester) {
        this.ID = ID;
        this.name = name;
        this.year = year;
        this.semester = semester;
        this.Students = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public static List<Course> loadCourses(ObjectMapper mapper, File file) throws Exception {
        if (!file.exists())
            return new ArrayList<>();
        return mapper.readValue(
                file,
                mapper.getTypeFactory().constructCollectionType(List.class, Course.class));
    }

    private static void saveCourses(ObjectMapper mapper, File file, List<Course> courses) throws Exception {
        new File("output").mkdirs();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, courses);
    }

    private static boolean isCourseExist(List<Course> courses, String courseID) {
        for (Course course : courses) {
            if (course.ID.equals(courseID))
                return true;
        }
        return false;
    }

    public void addStudent(String courseID, String studentName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/resources/courses.json");
        new File("output").mkdirs();
        List<Course> courses = loadCourses(mapper, file);

        if (isCourseExist(courses, courseID)) {
            for (Course course : courses) {
                if (course.ID.equals(courseID)) {
                    course.Students.add(studentName);
                    saveCourses(mapper, file, courses);
                }
            }

        } else {
            System.out.println("This Course doesnt Exist , try again");
        }

    }

    public void removeStudent(String courseID, String studentName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/resources/courses.json");
        List<Course> courses = loadCourses(mapper, file);

        if (!isCourseExist(courses, courseID)) {
            System.out.println("Course " + courseID + " doesn't exist.");
            return;
        }

        for (Course course : courses) {
            if (course.ID.equals(courseID)) {
                if (!course.Students.contains(studentName)) {
                    System.out.println(studentName + " is not registered in " + courseID + ".");
                    return;
                }
                course.Students.remove(studentName);
                saveCourses(mapper, file, courses);
                return;
            }
        }
    }

    public ArrayList<String> getRegisteredStudent(String courseName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/resources/courses.json");
        List<Course> courses = loadCourses(mapper, file);

        for (Course course : courses) {
            if (course.name.equals(courseName)) {
                return course.Students;
            }

        }

        System.out.println("No Registered Student in this Course");
        return null;

    }

    public static void addCourse(String courseID, String name, int year, int semester) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/resources/courses.json");
        List<Course> courses = loadCourses(mapper, file);
        Course course = new Course(courseID, name, year, semester);
        if (!isCourseExist(courses, courseID)) {
            courses.add(course);
            System.out.println("Course added successfully.");
        } else {
            System.out.println("This Course already exists.");
        }
        saveCourses(mapper, file, courses);
    }

    public static void editCourse(String courseID, String name, int year, int semester) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/resources/courses.json");
        List<Course> courses = loadCourses(mapper, file);

        if (!isCourseExist(courses, courseID)) {
            System.out.println("This Course is not Exist");
        } else {
            for (Course course : courses) {
                if (course.ID.equals(courseID)) {
                    course.name = name;
                    course.year = year;
                    course.semester = semester;
                }
            }
        }
        saveCourses(mapper, file, courses);
    }

    public static void deleteCourse(String courseID) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/resources/courses.json");
        List<Course> courses = loadCourses(mapper, file);

        if (!isCourseExist(courses, courseID)) {
            System.out.println("This Course doesn't Exist");
        } else {
            courses.removeIf(course -> courseID.equals(course.ID));
        }
        saveCourses(mapper, file, courses);
    }

    
}
