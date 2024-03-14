import javax.swing.*;
import java.awt.*;

// y = asin(x)+b 
public class SinDraw extends JFrame implements Runnable {

    JPanel panel = new JPanel();

    public void drawSin(String aStr, String bStr) {
            int a = Integer.parseInt(aStr);
            int b =  Integer.parseInt(bStr);
            Graphics g = panel.getGraphics();
            g.drawLine(panel.getWidth()/2, 0,panel.getWidth()/2,panel.getHeight());
            g.drawLine(0, panel.getHeight()/2,panel.getWidth(),panel.getHeight()/2);
            g.setColor(Color.BLUE);
            int startX = 0;
            int startY = panel.getHeight() / 2;
            int prevX = startX;
            int prevY = startY;
            for (int i = startX; i < panel.getWidth(); i++) {
                int y = (int) (Math.sin(a * (i - startX) * Math.PI / 180.0 + b) * 100) + startY;
                g.drawLine(prevX, prevY, i, y);
                prevX = i;
                prevY = y;
            }
        }
    @Override
    public void run(){
        setTitle("Drawing Window");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel inputPanel = new JPanel();
        JTextField aText = new JTextField(10);
        JTextField bText = new JTextField(10);
        JButton drawButton  =new JButton("Draw");
        drawButton.addActionListener(e->{
            this.drawSin(aText.getText(), bText.getText());
        });
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            panel.repaint();
        });
        inputPanel.add(aText);
        inputPanel.add(bText);
        inputPanel.add(drawButton);
        inputPanel.add(clearButton);
        // panel
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(inputPanel, BorderLayout.SOUTH);
        
        setVisible(true);  
    }
}