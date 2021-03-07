package view;

import control.PlayerSteering;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class View {
    public static final int WIDTH =  5 * ViewFragment.SIZE;
    public static final int HEIGHT = 5  * ViewFragment.SIZE;
    Screen screen = new Screen();
    Window window = new Window(screen);
    public void start(){
        EventQueue.invokeLater(this::run);
    }

    public View(PlayerSteering playerSteering) {
        window.jFrame.addKeyListener(playerSteering);
    }

    private void run() {
        window.create();
    }

    private class Window{
        JFrame jFrame = new JFrame();
        Screen screen;

        public Window(Screen screen) {
            this.screen = screen;
        }

        private void create() {
            jFrame.add(screen);
            jFrame.setSize(WIDTH, HEIGHT);
            jFrame.setLocationRelativeTo(null);
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jFrame.setVisible(true);
        }
    }

    public void update(java.util.List<ViewFragment> currentLevelViewComponents){
        screen.draw(currentLevelViewComponents);
    }



    private class Screen extends JPanel{
        java.util.List<ViewFragment> currentLevelViewComponents = new LinkedList<>();

        public void draw(java.util.List<ViewFragment> currentLevelViewComponents){
            this.currentLevelViewComponents = currentLevelViewComponents;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            currentLevelViewComponents.forEach(component -> {
                g2d.setColor(component.getColor());
                g2d.fillRect((int)component.getRectangle2D().getX(),(int)component.getRectangle2D().getY(),(int)component.getRectangle2D().getHeight(),(int)component.getRectangle2D().getHeight());
                g2d.draw(component.getRectangle2D());
            });
        }
    }

}


