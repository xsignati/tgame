package graphics;

import components.Sprite;
import config.ScreenSizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Renderer {
    public static final int WIDTH_SIZE = ScreenSizes.WIDTH_SIZE;
    public static final int HEIGHT_SIZE = ScreenSizes.HEIGHT_SIZE;
    private final Screen screen = new Screen();
    private final Window window = new Window(screen);
    private List<Sprite> objectsToDraw = new LinkedList<>();

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
            objectsToDraw.sort(Comparator.comparingInt(a -> a.priority));
            for (Sprite graphic : objectsToDraw){
                g2d.setColor(graphic.getColor());
                g2d.fill(graphic.getShape());
            }
        }
    }

    private static class Window{
        private final JFrame jFrame = new JFrame();
        private final Screen screen;

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


