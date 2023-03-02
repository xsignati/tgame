package input;

import components.Component;
import entities.Entity;
import events.EventBus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameController extends Component {
    public final InputController inputController = new InputController();
    Set<Integer> pressedKeys = inputController.getKeys();
    Map<Integer,Runnable> controls = new HashMap<>();
    public GameController(Entity entity, EventBus eventBus){
        super(entity, eventBus);
    }

    public void update(){
        pressedKeys.forEach(key -> controls.get(key).run());
    }

    public void addKey(int keyEvent, Runnable r){
        controls.put(keyEvent, r);
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
