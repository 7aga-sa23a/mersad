package haga_talga.model;

import java.util.Objects;

/**
 * Hey! This is the Student class
 * Contains student ID and name
 * Also has a function to parse QR code
 */
public class Student {
    
    /** Student ID */
    private String id;
    
    /** Student name */
    private String name;

    /** Empty constructor for JSON */
    public Student() {}

    /**
     * Constructor creates a Student
     */
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Function parses QR code and creates Student object
     * Supports different formats:
     * - "id|name" with pipe
     * - "id,name" with comma
     * - "id;name" with semicolon
     * 
     * Returns null if QR is not valid
     */
    public static Student fromQRData(String qrData) {
        if (qrData == null || qrData.trim().isEmpty()) {
            return null;
        }

        qrData = qrData.trim();

        String[] parts = null;

        // Try splitting with different delimiters
        if (qrData.contains("|")) {
            parts = qrData.split("\\|", 2);
        } else if (qrData.contains(",")) {
            parts = qrData.split(",", 2);
        } else if (qrData.contains(";")) {
            parts = qrData.split(";", 2);
        }

        // If we found 2 parts, create Student
        if (parts != null && parts.length >= 2) {
            String id = parts[0].trim();
            String name = parts[1].trim();
            return new Student(id, name);
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}