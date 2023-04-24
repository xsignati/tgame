package components;

import entity.Entity;
import config.ScreenSizes;
import events.EventBus;
import world.GameWorld;
import java.util.List;
import java.util.LinkedList;

public class Camera extends Component {
    Position position;
    public static List<Sprite> shapesToDraw = new LinkedList<>();

    public Camera(Entity entity, EventBus eventBus, Position position) {
        super(entity, eventBus);
        this.position = position;
    }

    public void update(){
        moveCameraIfFollowingNeeded();
        captureCurrentScreen();

    }

    private void captureCurrentScreen(){
        shapesToDraw.clear();
        GameWorld.getActiveComponents(Sprite.class).stream().filter(s -> s.getShape().x >= position.x - ScreenSizes.EVERY_ENTITY_SIZE && s.getShape().x < position.x + ScreenSizes.WIDTH_SIZE).forEach(
                m -> shapesToDraw.add(m.getCopyOfMovedSprite(position.x)));
    }

    private void moveCameraIfFollowingNeeded(){
        if(entity.position.x + entity.getComponent(Collider.class).hitBox.width >= position.x  + ScreenSizes.HALF_WIDTH_SIZE){
            position.x =  entity.position.x - ScreenSizes.HALF_WIDTH_SIZE + entity.getComponent(Collider.class).hitBox.width;
        }
    }

    public List<Sprite> getCapturedScreen(){
        return shapesToDraw;
    }
}
