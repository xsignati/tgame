package components;

import entity.Entity;
import events.EventBus;

import java.awt.*;

public class PlayerBehavior extends Behaviour {
    public PlayerBehavior(Entity entity, EventBus eventBus) {
        super(entity, eventBus);
    }

    @Override
    public void onCollision(Entity other, boolean[] isColliding) {
        if(other.tag.equals("floor")) {
            if (isColliding[0]) {
                entity.getComponent(Motion.class).setDx(0);
            }
            if (isColliding[1]) {
                entity.getComponent(Motion.class).setDy(0);
            }
        }
        if(other.tag.equals("background")){
            if(entity.getComponent(Motion.class).getX() + entity.getComponent(Motion.class).getWidth()*0.5 >= other.getComponent(Motion.class).getX()) {
                Sprite o = other.getComponent(Sprite.class);
                entity.getComponent(Sprite.class).setColor(new Color(o.getColor().getRed() , o.getColor().getGreen() - 170, o.getColor().getBlue() - 170,170));

            }
        }
    }

    @Override
    public void update() {
    }
}
