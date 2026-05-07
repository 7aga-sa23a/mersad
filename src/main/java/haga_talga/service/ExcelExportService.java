package haga_talga.service;

import haga_talga.model.AttendanceRecord;
import haga_talga.model.CourseData;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import haga_talga.service.CourseStudentJsonService.StudentData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * el service di b-texport el data l mlaf Excel
 * b-tst5dm FastExcel w b-t7t el 7adreen w el 8aybeen
 */
public class ExcelExportService {
    
    /** 3naween el 23meda b el 3araby */
    private static final String[] HEADERS = { "كود الطالب", "اسم الطالب", "وقت الحضور", "حضور" };

    /**
     * b-t3ml export ll Excel
     * b-tktb "حاضر" b el a5dr w "غياب" b el a7mr
     */
    public boolean exportToExcel(CourseData course, String filePath) {
        if (course == null || filePath == null)
            return false;
            
        List<AttendanceRecord> records = course.getRecords();
        CourseStudentJsonService jsonService = new CourseStudentJsonService();
        List<StudentData> allStudents = jsonService.getStudentsFromJson(course);
        
        if (records.isEmpty() && (allStudents == null || allStudents.isEmpty())) {
            System.out.println("No records to export.");
            return false;
        }

        // Create set of present IDs
        Set<String> attendedIds = records.stream()
                .map(AttendanceRecord::getStudentId)
                .collect(Collectors.toSet());

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            // Create new workbook
            Workbook workbook = new Workbook(fos, "Mersad Attendance", "1.0");
            Worksheet sheet = workbook.newWorksheet("Attendance");

            // Set column widths
            sheet.width(0, 15);  // ID
            sheet.width(1, 30); // Name
            sheet.width(2, 20); // Time
            sheet.width(3, 12); // Status

            // Row 0: Title
            sheet.value(0, 0, "كشف الحضور - " + course.getName());
            sheet.range(0, 0, 0, 3).merge();
            sheet.style(0, 0)
                    .bold()
                    .fontSize(16)
                    .horizontalAlignment("center")
                    .verticalAlignment("center")
                    .fillColor("0078D7")
                    .fontColor("FFFFFF")
                    .set();

            // Row 1: Headers
            sheet.value(1, 0, HEADERS[0]);
            sheet.value(1, 1, HEADERS[1]);
            sheet.value(1, 2, HEADERS[2]);
            sheet.value(1, 3, HEADERS[3]);
            sheet.range(1, 0, 1, 3).style()
                    .bold()
                    .fontSize(12)
                    .borderStyle("thin")
                    .fillColor("DCE6F1")
                    .fontColor("000000")
                    .horizontalAlignment("center")
                    .verticalAlignment("center")
                    .set();

            // Data
            int row = 2;
            int counter = 0;
            
            // 1. Present students first (with "حاضر")
            for (AttendanceRecord record : records) {
                sheet.value(row, 0, record.getStudentId());
                sheet.value(row, 1, record.getStudentName());
                sheet.value(row, 2, record.getFormattedTime());
                sheet.value(row, 3, "حاضر");

                String rowFill = (counter % 2 == 0) ? "F2F2F2" : "FFFFFF";

                sheet.range(row, 0, row, 2).style()
                        .borderStyle("thin")
                        .fillColor(rowFill)
                        .fontColor("000000")
                        .horizontalAlignment("center")
                        .verticalAlignment("center")
                        .set();

                sheet.range(row, 3, row, 3).style()
                        .borderStyle("thin")
                        .fillColor("92D050")  // green
                        .fontColor("FFFFFF")
                        .bold()
                        .horizontalAlignment("center")
                        .verticalAlignment("center")
                        .set();
                        
                row++;
                counter++;
            }
            
            // 2. Absent students
            if (allStudents != null) {
                for (StudentData student : allStudents) {
                    if (!attendedIds.contains(student.id)) {
                        sheet.value(row, 0, student.id);
                        sheet.value(row, 1, student.name);
                        sheet.value(row, 2, "-");
                        sheet.value(row, 3, "غياب");

                        String rowFill = (counter % 2 == 0) ? "F2F2F2" : "FFFFFF";

                        sheet.range(row, 0, row, 2).style()
                                .borderStyle("thin")
                                .fillColor(rowFill)
                                .fontColor("000000")
                                .horizontalAlignment("center")
                                .verticalAlignment("center")
                                .set();

                        sheet.range(row, 3, row, 3).style()
                                .borderStyle("thin")
                                .fillColor("C00000")  // red
                                .fontColor("FFFFFF")
                                .bold()
                                .horizontalAlignment("center")
                                .verticalAlignment("center")
                                .set();
                                
                        row++;
                        counter++;
                    }
                }
            }

            workbook.finish();
            System.out.println("Exported to: " + filePath);
            return true;

        } catch (IOException e) {
            System.out.println("Export error: " + e.getMessage());
            return false;
        }
    }

    /**
     * b-t3ml export f el folder el 2asasy
     */
    public boolean exportFinal(CourseData course) {
        File folder = new File("src/main/resources/attendanceExcle");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return exportToExcel(course, "src/main/resources/attendanceExcle/" + course.getExportFileName());
    }
}