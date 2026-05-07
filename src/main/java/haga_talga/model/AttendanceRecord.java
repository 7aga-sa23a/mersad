package haga_talga.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Hey! This is AttendanceRecord representing one attendance entry
 * Records the student that was scanned, the time, and status
 */
public class AttendanceRecord {
    
    /** Time format for display */
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** The student that was scanned */
    private Student student;
    
    /** Scan time */
    private LocalDateTime scanTime;
    
    /** Attendance status (default: Present) */
    private String status;

    public AttendanceRecord() {}

    /**
     * Constructor creates a new record
     * Status is automatically set to "Present"
     */
    public AttendanceRecord(Student student, LocalDateTime scanTime) {
        this.student = student;
        this.scanTime = scanTime;
        this.status = "Present";
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDateTime getScanTime() {
        return scanTime;
    }

    public void setScanTime(LocalDateTime scanTime) {
        this.scanTime = scanTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets formatted time as string
     */
    public String getFormattedTime() {
        return scanTime != null ? scanTime.format(FORMAT) : "";
    }

    /**
     * Gets student ID from the record
     */
    public String getStudentId() {
        return student != null ? student.getId() : "";
    }

    /**
     * Gets student name from the record
     */
    public String getStudentName() {
        return student != null ? student.getName() : "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceRecord that = (AttendanceRecord) o;
        return Objects.equals(student, that.student) &&
               Objects.equals(scanTime, that.scanTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, scanTime);
    }

    @Override
    public String toString() {
        return student + " at " + getFormattedTime();
    }
}