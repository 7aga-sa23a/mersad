package haga_talga.service;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Service el camera elly b-tfte7 el webcam w btwareek el preview w bt-l2ot el frames
 */
public class CameraService {
    
    /** object el webcam */
    private Webcam webcam;
    
    /** el panel elly byzhr feh el preview */
    private JPanel cameraPanel;
    
    /** el frame bta3 el preview */
    private JFrame previewFrame;
    
    /** 3shan t3ml mirror ll sora */
    private boolean mirrorImage;
    
    /** el frame el 7aly */
    private volatile BufferedImage currentFrame;

    /**
     * el constructor b-ysfr kol 7aga b-null f el awl
     */
    public CameraService() {
        this.webcam = null;
        this.cameraPanel = null;
        this.previewFrame = null;
        this.mirrorImage = true;  // Yes mirror so preview acts like a mirror
        this.currentFrame = null;
    }

    /**
     * b-tfta7 el camera w b-tzhr shashet el preview
     * w b-tsh8al thread 3shan t-l2ot el frames 3la tool
     */
    public boolean openCamera() {
        // Close any old camera first
        closeCamera();
        
        // Check if camera exists
        if (!hasCamera()) {
            System.out.println("No webcam found!");
            return false;
        }

        // Get default webcam
        webcam = Webcam.getDefault();
        if (webcam == null) {
            System.out.println("Cannot access webcam!");
            return false;
        }

        // Set VGA resolution (640x480)
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.open();

        // Create panel for preview
        cameraPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage frame = currentFrame;
                if (frame != null) {
                    Graphics2D g2d = (Graphics2D) g;
                    int w = getWidth();
                    int h = getHeight();
                    if (mirrorImage) {
                        // Draw mirrored for mirror-like preview
                        g2d.drawImage(frame, w, 0, -w, h, null);
                    } else {
                        g2d.drawImage(frame, 0, 0, w, h, null);
                    }
                }
            }
        };
        cameraPanel.setPreferredSize(new java.awt.Dimension(640, 480));
        cameraPanel.setBackground(java.awt.Color.BLACK);

        // Create preview window
        previewFrame = new JFrame("QR Scanner");
        previewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        previewFrame.add(cameraPanel, BorderLayout.CENTER);
        previewFrame.pack();
        previewFrame.setLocationRelativeTo(null);
        previewFrame.setAlwaysOnTop(true);
        previewFrame.setResizable(false);
        previewFrame.setVisible(true);

        // Start thread to capture frames
        Thread captureThread = new Thread(() -> {
            while (webcam != null && webcam.isOpen()) {
                try {
                    currentFrame = webcam.getImage();
                    if (cameraPanel != null && currentFrame != null) {
                        cameraPanel.repaint();
                    }
                    Thread.sleep(50);  // 20 FPS
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        captureThread.start();

        System.out.println("Camera opened successfully!");
        return true;
    }

    /**
     * b-t2fl el camera w b-tkaser el frame
     */
    public void closeCamera() {
        if (previewFrame != null) {
            previewFrame.dispose();
            previewFrame = null;
        }
        if (webcam != null && webcam.isOpen()) {
            webcam.close();
        }
        webcam = null;
        cameraPanel = null;
        currentFrame = null;
    }

    /**
     * b-tgib el frame el 7aly
     */
    public BufferedImage getCurrentFrame() {
        return currentFrame;
    }

    /**
     * b-tt2kd law el camera mfto7a
     */
    public boolean isCameraOpen() {
        return webcam != null && webcam.isOpen();
    }

    /**
     * b-tt2kd law feh cameras mtwsala aslan
     */
    public boolean hasCamera() {
        List<Webcam> webcams = Webcam.getWebcams();
        return webcams != null && !webcams.isEmpty();
    }

    /**
     * b-tgib kol el cameras el mo-ta7a
     */
    public static List<Webcam> getAvailableCameras() {
        return Webcam.getWebcams();
    }
}