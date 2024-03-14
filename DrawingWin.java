import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingWin extends JFrame implements Runnable{
    private int lastX, lastY;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DrawingWin();
            }
        });
    }
    @Override
    public void run(){
        setTitle("Drawing Window");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         JPanel panel = new JPanel();
        panel.addMouseListener((new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
            }

       
        }));
    
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e){
                Graphics g = panel.getGraphics();
                g.drawLine(lastX, lastY, e.getX(), e.getY());
                lastX = e.getX();
                lastY = e.getY();
            }
            });
        JPanel remotePanel = new JPanel();
        remotePanel.setLayout(new FlowLayout());
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            panel.repaint();
        });
        remotePanel.add(clearButton);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(remotePanel, BorderLayout.SOUTH);

        setVisible(true);    
    }
}