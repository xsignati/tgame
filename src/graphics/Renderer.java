package graphics;

import components.Sprite;
import config.ScreenSizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

public class Renderer {
    public static final int WIDTH_SIZE = ScreenSizes.WIDTH_SIZE;
    public static final int HEIGHT_SIZE = ScreenSizes.HEIGHT_SIZE;
    public static final int BASIC_UNIT_SIZE = ScreenSizes.BASIC_UNIT_SIZE;

    Screen screen = new Screen();
    Window window = new Window(screen);
    List<Sprite> objectsToDraw = new LinkedList<>();

    public void start(){
        EventQueue.invokeLater(this::run);
    }

    public void attachListener(KeyListener keyListener){
        window.jFrame.addKeyListener(keyListener);
    }
    public void update(List<Sprite> graphics){
        objectsToDraw = graphics;
        screen.repaint();
    }

    private void run() {
        window.create();
    }

    private class Screen extends JPanel{
        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            for (Sprite graphic : objectsToDraw){
                g2d.setColor(graphic.getColor());
                g2d.fill(graphic.getShape());
            }
        }
    }

    private static class Window{
        JFrame jFrame = new JFrame();
        Screen screen;

        public Window(Screen screen) {
            this.screen = screen;
        }

        private void create() {
            jFrame.add(screen);
            jFrame.setSize(WIDTH_SIZE, HEIGHT_SIZE);
            jFrame.setLocationRelativeTo(null);
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jFrame.setVisible(true);
        }
    }

}


