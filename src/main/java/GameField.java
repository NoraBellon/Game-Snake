import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameField extends JPanel implements ActionListener {
    private final int size = 300;
    private final int dotSize = 16;
    private final int allDots = 400;
    private Image dot;
    private Image apple;
    private Image ground;
    private int appleX;
    private int appleY;
    private int[] x = new int[allDots];
    private int[] y = new int[allDots];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean down = false;
    private boolean up = false;
    private boolean inGame = true;
    private int t = 250;

    public GameField() {
        setBackground(Color.white);

        loudImage();
        InitGame();
        addKeyListener(new KeyListener());
        setFocusable(true);
    }

    public void InitGame() {
        dots = 2;
        for (int i = 0; i < 0; i++) {
            x[i] = 48 - dotSize;
            y[i] = 48;
        }
        timer = new Timer(t, this);
        timer.start();
        createApple();

    }

    public void loudImage() {
        ImageIcon ap = new ImageIcon("C:\\Users\\user\\IdeaProjects\\Snake\\src\\main\\apple.png");
        //ap.setPreferredSize(new Dimension(16, 16));
        apple = ap.getImage();
        ImageIcon d = new ImageIcon("C:\\Users\\user\\IdeaProjects\\Snake\\src\\main\\resources\\а.png");
        dot = d.getImage();
        //ImageIcon back = new ImageIcon("C:\\Users\\User\\Documents\\NetBeansProjects\\Snake\\src\\main\\java\\com\\mycompany\\snake\\ground.png");
        // ground = back.getImage();
    }

    public void createApple() {
        appleX = new Random().nextInt(17) * dotSize;
        appleY = new Random().nextInt(17) * dotSize;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            //  g.drawImage(ground,0,0,this);
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        } else{
        String str = "Game Over";
        g.setColor(Color.black);
        g.drawString(str,125,50);}
    }

    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (left) {
            x[0] -= dotSize;
        }
        if (right) {
            x[0] += dotSize;
        }
        if (up) {
            y[0] -= dotSize;
        }
        if (down) {
            y[0] += dotSize;
        }
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++;
            createApple();
        }
        t += 50;
    }

    public void checkCollisions() {
        for (int i = dots; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }
        }
        if (x[0] > size) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if (y[0] > size) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollisions();
            move();
        }
        repaint();
    }


    class KeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT & !right) {
                left = true;
                up = false;
                down = false;
            } else if (key == KeyEvent.VK_RIGHT & !left) {
                right = true;
                up = false;
                down = false;
            } else if (key == KeyEvent.VK_DOWN & !up) {
                down = true;
                right = false;
                left = false;
            } else if (key == KeyEvent.VK_UP & !down) {
                up = true;
                right = false;
                left = false;
            }
        }
    }
}

