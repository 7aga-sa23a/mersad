package haga_talga.service;

import haga_talga.model.AttendanceRecord;
import haga_talga.model.CourseData;
import haga_talga.model.Student;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Ya basha! el service di bthandle el 8yab w el 7door w2t el scan session
 * w btshof law feh duplicates w bt-save el records
 */
public class AttendanceService {
    
    /** 3shan n2ra el mwaad */
    private final CourseReader courseReader;
    
    /** el service bta3t el JSON */
    private final CourseStudentJsonService jsonService;
    
    /** el mada el 7alya */
    private CourseData currentCourse;
    
    /** list el talaba el 7adreen f el session di */
    private final List<AttendanceRecord> currentSession;

    /**
     * el constructor b-y3ml initialize ll services
     */
    public AttendanceService(CourseReader courseReader) {
        this.courseReader = courseReader;
        this.jsonService = new CourseStudentJsonService();
        this.currentCourse = null;
        this.currentSession = new ArrayList<>();
    }

    /**
     * b-tset el mada elly sh8aleen 3leha dlw2t
     */
    public void setCurrentCourse(CourseData course) {
        this.currentCourse = course;
        currentSession.clear();
        if (course != null) {
            currentSession.addAll(course.getRecords());
        }
    }

    public CourseData getCurrentCourse() {
        return currentCourse;
    }

    /**
     * b-tdef el taleb w btt2kd eno msh mtkrr 2bl ma tdefo
     */
    public boolean addStudent(Student student) {
        if (student == null || currentCourse == null) {
            return false;
        }

        // Check if student is already scanned
        if (isDuplicate(student)) {
            System.out.println("[DUPLICATE] Already recorded: " + student.getName());
            return false;
        }

        // Create new record
        AttendanceRecord record = new AttendanceRecord(student, LocalDateTime.now());
        currentSession.add(record);
        currentCourse.addRecord(record);

        // Add to JSON
        jsonService.addStudentToJson(currentCourse, student);

        return true;
    }

    /**
     * b-tshof law el taleb da 3ml scan 2bl keda wala la
     */
    public boolean isDuplicate(Student student) {
        return currentSession.stream().anyMatch(r -> r.getStudent().equals(student));
    }

    /**
     * b-tgib kol el records
     */
    public List<AttendanceRecord> getCurrentRecords() {
        return new ArrayList<>(currentSession);
    }

    /**
     * b-tgib 3dad el talaba elly 3mlo scan
     */
    public int getTotalCount() {
        return currentSession.size();
    }

    /**
     * b-tfdy el session
     */
    public void clearSession() {
        currentSession.clear();
    }

    /**
     * b-tsave el session w b-tzwd 3dad el 7door f el JSON
     */
    public void saveSessionToCounters() {
        if (currentCourse != null) {
            jsonService.incrementAttendanceCounters(currentCourse);
        }
    }
}