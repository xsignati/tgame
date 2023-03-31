package physics;

import components.Motion;
import components.PlayerBehavior;
import entity.Entity;
import game.GameManager;
import world.GameWorld;

import java.awt.*;

public class CollisionDetector {
    public CollisionDetector() {
    }

    private boolean[] detectCollision(Entity first, Entity second) {
        Motion m1 = first.getComponent(Motion.class);
        Motion m2 = second.getComponent(Motion.class);
        Rectangle withAddedDx = new Rectangle(m1.getX() + m1.getDx(),m1.getY(),m1.getWidth(), m1.getHeight() );
        Rectangle withAddedDy = new Rectangle(m1.getX(),m1.getY() + m1.getDy(), m1.getWidth(), m1.getHeight() );
        Rectangle otherCollider = new Rectangle(m2.getX() + m2.getDx(),m2.getY() + m2.getDy(),m2.getWidth(),m2.getHeight());
        return new boolean[]{withAddedDx.intersects(otherCollider),withAddedDy.intersects(otherCollider)};
    }

    public void update() {
        for (Entity first : GameWorld.entities){
            for (Entity second : GameWorld.entities) {
                if (first != second && first.collidable && second.collidable) {
                    boolean[] isColliding = detectCollision(first,second);
                    if(isColliding[0] || isColliding[1]){
                        PlayerBehavior behavior = first.getComponent(PlayerBehavior.class);
                        if(behavior != null)
                            behavior.onCollision(second, isColliding);
                    }
                }
            }
        }
    }
}
