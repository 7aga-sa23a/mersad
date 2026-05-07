package haga_talga.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseData {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** maps to "name" in JSON */
    @SerializedName("name")
    private String name;

    /** maps to "ID" in JSON */
    @SerializedName("ID")
    private String courseCode;

    /** derived from Students list size */
    @SerializedName("Students")
    private List<String> students;

    /** derived from year + semester (not stored, computed) */
    private transient LocalDate date;

    /** stored as "year" in JSON */
    @SerializedName("year")
    private int year;

    /** stored as "semester" in JSON */
    @SerializedName("semester")
    private int semester;

    /** not stored in JSON */
    private transient List<AttendanceRecord> records;

    public CourseData() {
        this.records = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    // ── after deserialization, compute LocalDate from year + semester ──────────
    // semester 1 → Jan 1, semester 2 → Jul 1
    public LocalDate getDate() {
        if (date == null && year > 0) {
            int month = (semester == 2) ? 7 : 1;
            date = LocalDate.of(year, month, 1);
        }
        return date;
    }

    public String getName()                        { return name; }
    public String getCourseCode()                  { return courseCode; }
    public int getNumberOfStudents()               { return students != null ? students.size() : 0; }
    public List<String> getStudents()              { return students; }
    public int getYear()                           { return year; }
    public int getSemester()                       { return semester; }

    public List<AttendanceRecord> getRecords()     { return records; }
    public void addRecord(AttendanceRecord record) { this.records.add(record); }
    public int getRecordCount()                    { return records.size(); }

    public String getFormattedDate() {
        return getDate() != null ? getDate().format(DATE_FORMAT) : "";
    }

    public String getDisplayName() {
        return name + "-" + courseCode + "-" + year;
    }

    public String getExportFileName() {
        return getDisplayName() + "_" + LocalDate.now().format(DATE_FORMAT) + ".xlsx";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseData course = (CourseData) o;
        return Objects.equals(name, course.name) &&
               Objects.equals(courseCode, course.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, courseCode);
    }

    @Override
    public String toString() {
        return getDisplayName() + " - " + getRecordCount() + " records";
    }
}