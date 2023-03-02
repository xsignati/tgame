package components;

import entities.Entity;
import game.GameManager;
import config.ScreenSizes;
import events.EventBus;

import java.awt.*;
import java.util.LinkedList;
import java.util.Objects;

public class Camera extends Component {
    private final Entity player;
    public int currentX = 0;
    public java.util.List<Sprite> shapesToDraw = new LinkedList<>();

    public Camera(Entity entity, EventBus eventBus, Entity player) {
        super(entity, eventBus);
        this.player = player;
    }

    Shape cameraFrame = new Rectangle(0 ,0,ScreenSizes.WIDTH_SIZE,ScreenSizes.HEIGHT_SIZE);
    public void update(){
        Motion playerMotion = player.getComponent(Motion.class);

        shapesToDraw.clear();
        GameManager.entities.stream().map(e -> e.getComponent(Sprite.class)).filter(Objects::nonNull).forEach(
        m -> {
            Rectangle currentShape = m.getShape();
            if(playerMotion.getX() + playerMotion.getWidth() + playerMotion.getDx()  >= currentX + ScreenSizes.WIDTH_SIZE * 0.5){
                currentX = (int)(playerMotion.getX() + playerMotion.getWidth() - ScreenSizes.WIDTH_SIZE * 0.5);
            }
            cameraFrame = new Rectangle(currentX , 0,ScreenSizes.WIDTH_SIZE,ScreenSizes.HEIGHT_SIZE);
            if(cameraFrame.intersects(currentShape)){
                Rectangle shapeDraw = new Rectangle((int)currentShape.getX() - currentX,(int)currentShape.getY(), (int)currentShape.getWidth(), (int)currentShape.getHeight());
                shapesToDraw.add(new Sprite(null,null,m.getColor(),shapeDraw,null));
            }
        });
    }
}
