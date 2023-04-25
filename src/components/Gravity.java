package components;

import config.ScreenSizes;
import entity.Entity;
import events.EventBus;

import static config.ScreenSizes.BASIC_UNIT_SIZE;
import static config.ScreenSizes.HALF_BASIC_UNIT_SIZE;

public class Gravity extends Component {
    public static final int GRAVITY = 5;
    private final Motion motion;

    public Gravity(Entity entity, EventBus eventBus, Motion motion){
        super(entity, eventBus);
        this.motion = motion;
    }

    public void update(){
        motion.accelerateDy(GRAVITY);
    }
}
