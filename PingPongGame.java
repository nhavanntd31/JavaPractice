import java.util.Vector;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PingPongGame extends JFrame implements Runnable{
    private Vector<Pair<Integer,Integer>> ballCenter = new Vector<>();
    private JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBall(g);
        }
    };
    private int score = 0;
    private Vector<Pair<Integer,Integer>> moveVector = new Vector<>();
    private int radius = 15;
    private boolean gameOver = false;
    public void drawBall(Graphics g){
        
        for (Pair<Integer,Integer> center : ballCenter) {
            g.setColor(Color.BLUE);
            g.fillOval(center.getX(), center.getY(), radius, radius);
        }
    }
    public void moveBall(){
        for (int i = 0; i < ballCenter.size(); i++) {
            int x = ballCenter.elementAt(i).getX();
            int y = ballCenter.elementAt(i).getY();
            int moveX = moveVector.elementAt(i).getX();
            int moveY= moveVector.elementAt(i).getY();
            int newX = x + moveX;
            int newY = y + moveY;
            ballCenter.elementAt(i).setXY(newX,newY); 
        }
    }
    public void checkCollison(){
        for (Pair<Integer,Integer> center : ballCenter) {
            int x = center.getX();
            int y = center.getY();
            if (x <= 0 || x >= panel.getWidth() || y <= 0 || y >= panel.getHeight()) {
                gameOver = true;
                
            }
        }
    }
    private boolean isInsideBall(int x, int y, int ballX, int ballY) {
        int centerX = ballX + radius / 2;
        int centerY = ballY + radius / 2;
        double distance = Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2));
        return distance <= radius / 2;
    }
    
    @Override
    public void run(){
        setTitle("Simple Paint App");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ballCenter.add(new Pair<Integer,Integer>(200, 200));
        moveVector.add(new Pair<Integer,Integer>(2, 2));
        JPanel scorePanel = new JPanel();
        JLabel scoreLabel = new JLabel("Score:" + Integer.toString(score));
        scoreLabel.setSize(200, 200);
        scorePanel.add(scoreLabel);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(scoreLabel, BorderLayout.SOUTH);
        Random random = new Random();
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i = ballCenter.size() - 1; i >= 0; i--) {
                    int ballX = ballCenter.elementAt(i).getX();
                    int ballY = ballCenter.elementAt(i).getY();
        
                    if (!gameOver && isInsideBall(e.getX(), e.getY(), ballX, ballY)) {
                        score++;
                        scoreLabel.setText("Score: " + score);
                        ballCenter.remove(i);
                        moveVector.remove(i);
        
                        int moveX1 = random.nextInt(11) - 5;
                        int moveX2 = random.nextInt(11) - 5;
                        int moveY1 = random.nextInt(11) - 5;
                        int moveY2 = random.nextInt(11) - 5;
                        ballCenter.add(new Pair<>(ballX, ballY));
                        ballCenter.add(new Pair<>(ballX, ballY));
                        moveVector.add(new Pair<>(moveX1, moveY1));
                        moveVector.add(new Pair<>(moveX2, moveY2));
                        
                        break;
                    }
                }
            }
        });


        setVisible(true);
        while (!gameOver) {
            
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            panel.repaint();
            moveBall();
            checkCollison();
        }
        if (gameOver) {
          scoreLabel.setText("Game over !! Final score: " + score);
      }
    }
}
