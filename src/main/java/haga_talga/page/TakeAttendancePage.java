package haga_talga.page;

import haga_talga.model.CourseData;
import haga_talga.model.Student;
import haga_talga.service.CourseReader;
import haga_talga.service.CameraService;
import haga_talga.service.QRScannerService;
import haga_talga.service.AttendanceService;
import haga_talga.service.ExcelExportService;

import java.util.List;
import java.util.Scanner;

// el class da hwa sf7et a5d el 8yab w el 7door b-st5dam el QR Scanner
public final class TakeAttendancePage extends Page {
    private final CourseReader courseReader;
    private final CameraService cameraService;
    private final QRScannerService qrScannerService;
    private final AttendanceService attendanceService;
    private final ExcelExportService excelService;
    private final Scanner scanner;

    // el constructor b-yجهز kol el services elly hn-7tagha
    public TakeAttendancePage() {
        // b-nsale7 mshklet el araby f el terminal
        try {
            System.setOut(new java.io.PrintStream(new java.io.FileOutputStream(java.io.FileDescriptor.out), true, java.nio.charset.StandardCharsets.UTF_8.name()));
        } catch (java.io.UnsupportedEncodingException e) {
            // ignore
        }
        this.courseReader = new CourseReader();
        this.cameraService = new CameraService();
        this.qrScannerService = new QRScannerService(cameraService);
        this.attendanceService = new AttendanceService(courseReader);
        this.excelService = new ExcelExportService();
        this.scanner = new Scanner(System.in);
    }

    // b-tzhr el sf7a w tbda2 el workflow bta3 a5d el 8yab
    @Override
    public String display() {
        System.out.println("\n========================================");
        System.out.println("     TAKE ATTENDANCE (QR SCANNER)");
        System.out.println("========================================\n");

        if (!selectCourse()) {
            return "DashboardPage";
        }

        startScanningSession();
        
        return "DashboardPage";
    }

    // b-t5ly el user y5tar el mada mn el list
    private boolean selectCourse() {
        List<CourseData> courses = courseReader.getCourses();
        if (courses.isEmpty()) {
            System.out.println("No course available. Please add a course first.");
            System.out.println("Press Enter to return to Dashboard...");
            scanner.nextLine();
            return false;
        }

        System.out.println("=== SELECT COURSE ===");
        for (int i = 0; i < courses.size(); i++) {
            CourseData c = courses.get(i);
            System.out.println((i + 1) + ". " + c.getDisplayName());
        }
        System.out.print("Select (0 to cancel): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) return false;

            CourseData course = courseReader.getCourse(choice - 1);
            if (course != null) {
                attendanceService.setCurrentCourse(course);
                return true;
            } else {
                System.out.println("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
        return false;
    }

    // b-tbda2 session el scan w tfta7 el camera
    private void startScanningSession() {
        CourseData course = attendanceService.getCurrentCourse();
        if (course == null) {
            System.out.println("No course selected.");
            return;
        }

        System.out.println("\n=== ATTENDANCE SESSION STARTED ===");
        System.out.println("Course: " + course.getDisplayName());
        System.out.println("Expected Students: " + course.getNumberOfStudents());
        System.out.println();

        if (!cameraService.openCamera()) {
            System.out.println("Failed to open camera.");
            return;
        }

        qrScannerService.startScanning();
        scanLoop();
    }

    // el loop el 2asasy elly by2ra mn el camera w ytsna el user y-save aw y-exit
    private void scanLoop() {
        System.out.println("Type 'y' + Enter to save and quit, or 'n' + Enter to exit without saving.");
        System.out.println("(You can also just close the camera window to automatically save and finish)");
        
        boolean actionTaken = false;

        while (qrScannerService.isRunning() && cameraService.isCameraOpen()) {
            Student student = qrScannerService.getLastScannedStudent();
            if (student != null) {
                boolean added = attendanceService.addStudent(student);
                if (added) {
                    String time = java.time.LocalDateTime.now().format(
                            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    System.out.println("\n[SCANNED] " + student.getId() + " - " + student.getName() + " at " + time);
                }
                qrScannerService.clearLastScanned();
            }

            System.out.print("\rScanned: " + qrScannerService.getScannedCount() + " | Total: " + attendanceService.getTotalCount());

            try {
                if (System.in.available() > 0) {
                    String input = scanner.nextLine().trim();
                    if (input.equalsIgnoreCase("y")) {
                        saveAndQuit();
                        actionTaken = true;
                        break;
                    } else if (input.equalsIgnoreCase("n")) {
                        exitWithoutSave();
                        actionTaken = true;
                        break;
                    }
                }
            } catch (java.io.IOException e) {
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }

        if (!actionTaken) {
            System.out.println("\n\nCamera window closed. Auto-saving attendance...");
            saveAndQuit();
        } else {
            qrScannerService.stopScanning();
            cameraService.closeCamera();
        }
        
        System.out.println("\nPress Enter to return to Dashboard...");
        scanner.nextLine();
    }

    // b-twa2f el camera w t-save el Excel w el JSON
    private void saveAndQuit() {
        qrScannerService.stopScanning();
        cameraService.closeCamera();

        CourseData course = attendanceService.getCurrentCourse();
        
        if (course != null && course.getRecordCount() > 0) {
            boolean success = excelService.exportFinal(course);
            if (success) {
                System.out.println("\n\nAttendance saved! (" + course.getRecordCount() + " records)");
                System.out.println("File: src/main/resources/attendanceExcle/" + course.getExportFileName());
                attendanceService.saveSessionToCounters();
            } else {
                System.out.println("\nFailed to save Excel file.");
            }
        } else {
            System.out.println("\nNo attendance records to save.");
        }
    }

    // b-twa2f el camera mn 8er ma t-save 7aga
    private void exitWithoutSave() {
        qrScannerService.stopScanning();
        cameraService.closeCamera();
        System.out.println("\nAttendance not saved.");
    }
}
