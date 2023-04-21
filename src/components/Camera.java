package components;

import entity.Entity;
import config.ScreenSizes;
import events.EventBus;
import world.GameWorld;
import java.util.List;
import java.util.LinkedList;

public class Camera extends Component {
    Position position;
    public List<Sprite> shapesToDraw = new LinkedList<>();

    public Camera(Entity entity, EventBus eventBus, Position position) {
        super(entity, eventBus);
        this.position = position;
    }


    public void update(){
        moveCameraIfFollowingNeeded();
        captureScreen();

    }

    private void captureScreen(){
        shapesToDraw.clear();
        GameWorld.getActiveComponents(Sprite.class).forEach(
                m -> shapesToDraw.add(m.getCopyOfMovedSprite(position.x)));
    }

    private void moveCameraIfFollowingNeeded(){
        if(entity.position.x >= position.x + ScreenSizes.WIDTH_SIZE * 0.5){
            position.x = (int)(entity.position.x - ScreenSizes.WIDTH_SIZE * 0.5);
        }
    }

    public List<Sprite> getCapturedScreen(){
        return shapesToDraw;
    }
}
