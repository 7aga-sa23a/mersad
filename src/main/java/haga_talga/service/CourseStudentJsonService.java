package haga_talga.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import haga_talga.model.CourseData;
import haga_talga.model.Student;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * el service di b-tdir data el talaba f el JSON
 * bt-save el talaba w 3dad ayam el 7door f mlafat JSON
 */
public class CourseStudentJsonService {
    
    /** object el Gson */
    private final Gson gson;

    public CourseStudentJsonService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Class b-ymsl shakl mlaf el JSON
     */
    public static class CourseStudentsJson {
        public String Coursename;
        public String courseCode;
        public List<StudentData> Students;

        public CourseStudentsJson() {
            this.Students = new ArrayList<>();
        }

        public CourseStudentsJson(String courseName, String courseCode) {
            this.Coursename = courseName;
            this.courseCode = courseCode;
            this.Students = new ArrayList<>();
        }
    }

    /**
     * Class b-ymsl data el taleb el wa7ed
     */
    public static class StudentData {
        public String name;
        public String id;
        public int attendedDays;
        public String lastAttendedDate;

        public StudentData() {}

        public StudentData(String name, String id) {
            this.name = name;
            this.id = id;
            this.attendedDays = 0;
            this.lastAttendedDate = "";
        }
    }

    private String getJsonFileName(CourseData course) {
        return course.getName() + " student.json";
    }

    private String getJsonFilePath(CourseData course) {
        return "src/main/resources/CourseStudent/" + getJsonFileName(course);
    }

    private Path getJsonDirectory() {
        return Paths.get("src/main/resources/CourseStudent");
    }

    /**
     * b-t2ra el JSON el mawgod f el mlaf
     */
    private CourseStudentsJson loadExistingJson(CourseData course) {
        File file = new File(getJsonFilePath(course));
        if (!file.exists()) {
            return null;
        }
        try {
            String json = new String(Files.readAllBytes(file.toPath()));
            return gson.fromJson(json, CourseStudentsJson.class);
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("[JSON] Error reading file: " + e.getMessage());
            return null;
        }
    }

    private void ensureDirectoryExists() {
        try {
            Files.createDirectories(getJsonDirectory());
        } catch (IOException e) {
            System.out.println("[JSON] Error creating directory: " + e.getMessage());
        }
    }

    /**
     * b-tdef taleb gded ll JSON
     */
    public boolean addStudentToJson(CourseData course, Student student) {
        if (course == null || student == null) {
            return false;
        }

        ensureDirectoryExists();
        String filePath = getJsonFilePath(course);

        CourseStudentsJson jsonData = loadExistingJson(course);
        boolean isNewFile = false;

        if (jsonData == null) {
            jsonData = new CourseStudentsJson(course.getName(), course.getCourseCode());
            isNewFile = true;
        }

        if (isStudentAlreadyInJson(jsonData, student)) {
            System.out.println("[JSON] Student already exists: " + student.getName());
            return false;
        }

        jsonData.Students.add(new StudentData(student.getName(), student.getId()));

        return saveJsonToFile(filePath, jsonData, isNewFile);
    }

    private boolean isStudentAlreadyInJson(CourseStudentsJson jsonData, Student student) {
        return jsonData.Students.stream()
                .anyMatch(s -> s.id.equals(student.getId()));
    }

    /**
     * b-tsave el JSON f el mlaf
     */
    private boolean saveJsonToFile(String filePath, CourseStudentsJson jsonData, boolean isNewFile) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(jsonData, writer);
            String action = isNewFile ? "Created" : "Updated";
            String fileName = new File(filePath).getName();
            System.out.println("[" + action + " JSON] " + fileName);
            System.out.println("[JSON] Saved: " + filePath + " (Total students: " + jsonData.Students.size() + ")");
            return true;
        } catch (IOException e) {
            System.out.println("[JSON] Error saving file: " + e.getMessage());
            return false;
        }
    }

    public boolean syncWithExistingRecords(CourseData course) {
        if (course == null || course.getRecords() == null) {
            return false;
        }

        ensureDirectoryExists();
        String filePath = getJsonFilePath(course);

        CourseStudentsJson jsonData = loadExistingJson(course);
        boolean isNewFile = false;

        if (jsonData == null) {
            jsonData = new CourseStudentsJson(course.getName(), course.getCourseCode());
            isNewFile = true;
        }

        int addedCount = 0;
        for (var record : course.getRecords()) {
            Student student = record.getStudent();
            if (!isStudentAlreadyInJson(jsonData, student)) {
                jsonData.Students.add(new StudentData(student.getName(), student.getId()));
                addedCount++;
            }
        }

        if (addedCount > 0 || isNewFile) {
            return saveJsonToFile(filePath, jsonData, isNewFile);
        }

        return true;
    }

    public boolean deleteJsonFile(CourseData course) {
        File file = new File(getJsonFilePath(course));
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * b-tgib el talaba mn el JSON
     */
    public List<StudentData> getStudentsFromJson(CourseData course) {
        CourseStudentsJson jsonData = loadExistingJson(course);
        return jsonData != null ? jsonData.Students : new ArrayList<>();
    }

    /**
     * b-tzwd 3dad ayam el 7door
     * bs law el taleb mtktebsh 7ader el nahrda
     */
    public void incrementAttendanceCounters(CourseData course) {
        if (course == null || course.getRecords().isEmpty()) {
            return;
        }

        String filePath = getJsonFilePath(course);
        CourseStudentsJson jsonData = loadExistingJson(course);
        if (jsonData == null) {
            return;
        }

        boolean updated = false;
        String today = java.time.LocalDate.now().toString();
        
        for (var record : course.getRecords()) {
            for (StudentData student : jsonData.Students) {
                if (student.id.equals(record.getStudentId())) {
                    if (!today.equals(student.lastAttendedDate)) {
                        student.attendedDays++;
                        student.lastAttendedDate = today;
                        updated = true;
                    }
                    break;
                }
            }
        }

        if (updated) {
            saveJsonToFile(filePath, jsonData, false);
        }
    }
}