package input;

import components.Component;
import entity.Entity;
import events.EventBus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameController  {
    public static final InputController inputController = new InputController();
    static Set<Integer> pressedKeys = inputController.getKeys();

    public static boolean isPushed(int keyEvent){
        return pressedKeys.contains(keyEvent);
    }

    static class InputController implements KeyListener {
        private final Set<Integer> pressedKeys = new HashSet<>();

        public void keyPressed(KeyEvent e) {
            pressedKeys.add(e.getKeyCode());
        }
        public void keyReleased(KeyEvent e) {
            pressedKeys.remove(e.getKeyCode());
        }
        public void keyTyped(KeyEvent e) {}

        public Set<Integer> getKeys() {
            return pressedKeys;
        }
    }
}
