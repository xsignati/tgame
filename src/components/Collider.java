package components;

import entity.Entity;
import events.EventBus;

import java.awt.*;

public class Collider extends Component{
    Position position = entity.position;
    Motion motion;
    public  Rectangle hitBox;
    private final Rectangle hitBoxWithDx;
    private final Rectangle hitBoxWithDy;
    private final Rectangle hitBoxWithDxDy;
    public Collider(Entity entity, EventBus eventBus, Motion motion, int width, int height) {
        super(entity, eventBus);
        this.motion = motion;
        hitBox = new Rectangle(position.x,position.y,width,height);
        hitBoxWithDx = new Rectangle(position.x + motion.dx,position.y,hitBox.width,hitBox.height);
        hitBoxWithDy = new Rectangle(position.x,position.y + motion.dy,hitBox.width,hitBox.height);
        hitBoxWithDxDy = new Rectangle(position.x + motion.dx,position.y + motion.dy,hitBox.width,hitBox.height);
    }

    public boolean[] collidesWith(Collider other){
        boolean isCollidingX = hitBoxWithDx.intersects(other.hitBoxWithDxDy);
        boolean isCollidingY = hitBoxWithDy.intersects(other.hitBoxWithDxDy);
        return new boolean[]{isCollidingX,isCollidingY};

    }

    @Override
    public void update() {
        hitBox.setLocation(position.x,position.y);
        hitBoxWithDx.setLocation(position.x + motion.dx,position.y);
        hitBoxWithDy.setLocation(position.x,position.y + motion.dy);
        hitBoxWithDxDy.setLocation(position.x + motion.dx,position.y + motion.dy);
    }

}
