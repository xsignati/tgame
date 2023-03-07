package components;

import entity.Entity;
import events.EventBus;

public class Gravity extends Component {
    public static final int GRAVITY = 5;
    private final Motion motion;

    public Gravity(Entity entity, EventBus eventBus, Motion motion){
        super(entity, eventBus);
        this.motion = motion;
    }

    public void update(){
        motion.setDy(motion.getDy() + GRAVITY);
    }
}
