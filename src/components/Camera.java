package components;

import entity.Entity;
import game.GameManager;
import config.ScreenSizes;
import events.EventBus;
import world.GameWorld;

import java.awt.*;
import java.util.LinkedList;
import java.util.Objects;

public class Camera extends Component {
    private Entity entityToFollow;
    public int currentX = 0;
    public java.util.List<Sprite> shapesToDraw = new LinkedList<>();

    public Camera(Entity entity, EventBus eventBus, Entity player) {
        super(entity, eventBus);
        this.entityToFollow = player;
    }

    Shape cameraFrame = new Rectangle(0 ,0,ScreenSizes.WIDTH_SIZE,ScreenSizes.HEIGHT_SIZE);

    public void setEntityToFollow(Entity entity){
        entityToFollow = entity;
    }

    public void update(){
        Motion playerMotion = entityToFollow.getComponent(Motion.class);

        shapesToDraw.clear();
        //change to dont break TDA - e.g computeNextMove
        if(playerMotion.getX() + playerMotion.getWidth() + playerMotion.getDx()  >= currentX + ScreenSizes.WIDTH_SIZE * 0.5){
            currentX = (int)(playerMotion.getX() + playerMotion.getWidth() - ScreenSizes.WIDTH_SIZE * 0.5);
        }

        GameWorld.entities.stream().map(e -> e.getComponent(Sprite.class)).filter(Objects::nonNull).forEach(
        m -> {
            Rectangle currentShape = m.getShape();
            Rectangle shapeDraw = new Rectangle((int)currentShape.getX() - currentX,(int)currentShape.getY(), (int)currentShape.getWidth(), (int)currentShape.getHeight());
            Sprite sprite = new Sprite(null,null,m.getColor(),shapeDraw,null);
            if(m.priority == 1){sprite.priority = 1;};
            shapesToDraw.add(sprite);
        });
    }
}
