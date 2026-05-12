package haga_talga.service;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import haga_talga.model.Student;
import haga_talga.util.Formatter;

/**
 * el service di b-tscan el QR codes mn el camera
 * b-tst5dm ZXing w b-tsh8al f thread lwa7dha 3shan tscan 3la tool
 */
public class QRScannerService {
    
    /** reference l service el camera */
    private final CameraService cameraService;
    
    /** el reader bta3 ZXing */
    private final MultiFormatReader reader;
    
    /** set feha el IDs elly 3mlolha scan 3shan n-mn3 el tkrrar */
    private final Set<String> scannedIds;
    
    /** flag 3shan y-wa2f el loop */
    private boolean running;
    
    /** flag 3shan y-wa2f m2ktn */
    private boolean paused;
    
    /** a5er taleb 3ml scan */
    private Student lastScannedStudent;
    
    /** el w2t ben kol mo7awla w el tanya */
    private static final int SCAN_DELAY = 300;

    /**
     * el constructor bya5od CameraService
     */
    public QRScannerService(CameraService cameraService) {
        this.cameraService = cameraService;
        this.reader = new MultiFormatReader();
        this.scannedIds = new HashSet<>();
        this.running = false;
        this.paused = false;
        this.lastScannedStudent = null;
    }

    /**
     * b-tbda2 el scan f thread gded
     */
    public void startScanning() {
        if (!cameraService.isCameraOpen()) {
            Formatter.error("Camera not open!");
            return;
        }
        running = true;
        paused = false;
        scannedIds.clear();
        Thread scanThread = new Thread(this::scanLoop);
        scanThread.start();
    }

    /**
     * el loop el 2asasy bta3 el scan
     * b-ya5od frames w by-decode el QR
     */
    private void scanLoop() {
        while (running) {
            if (paused || !cameraService.isCameraOpen()) {
                sleep(100);
                continue;
            }
            try {
                // Get frame from camera
                BufferedImage image = cameraService.getCurrentFrame();
                if (image == null) {
                    sleep(SCAN_DELAY);
                    continue;
                }
                
                // Try to decode QR
                Result result = decodeQR(image);
                if (result != null) {
                    String qrData = result.getText();
                    
                    // Parse QR to Student
                    Student student = Student.fromQRData(qrData);
                    if (student != null && !scannedIds.contains(student.getId())) {
                        // New student add it
                        scannedIds.add(student.getId());
                        // Formatter.success("\nScanned a student with ID: " + student.getId());
                        lastScannedStudent = student;
                    }
                }
                sleep(SCAN_DELAY);
            } catch (Exception e) {
                Formatter.error("An error has occured while scanning.");
                sleep(SCAN_DELAY);
            }
        }
    }

    /**
     * b-t-decode el QR mn el sora
     */
    private Result decodeQR(BufferedImage image) {
        try {
            int width = image.getWidth();
            int height = image.getHeight();
            if (width <= 0 || height <= 0) return null;
            
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            return reader.decode(bitmap);
        } catch (NotFoundException e) {
            return null;
        } catch (Exception e) {
            Formatter.error("An error has occured while decoding.");
            return null;
        }
    }

    /**
     * b-twa2f el loop
     */
    public void stopScanning() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    /**
     * b-tgib a5er taleb 3ml scan
     */
    public Student getLastScannedStudent() {
        return lastScannedStudent;
    }

    /**
     * b-tmsa7 a5er taleb b3d ma ttsgl 7dooro
     */
    public void clearLastScanned() {
        lastScannedStudent = null;
    }

    /**
     * b-tgib 3dad el talaba elly 3mlo scan mn 8er tkrrar
     */
    public int getScannedCount() {
        return scannedIds.size();
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}