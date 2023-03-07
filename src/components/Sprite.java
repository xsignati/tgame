package components;

import entity.Entity;
import events.EventBus;

import java.awt.*;

public class Sprite extends Component {
    private final Motion motion;
    private Color color;
    private Rectangle shape;
    public int priority = 0;

    public Sprite(Entity entity, EventBus eventBus, Color color, Rectangle shape, Motion motion) {
        super(entity, eventBus);
        this.color = color;
        this.shape = shape;
        this.motion = motion;
    }

    public void update() {
        shape.setLocation(  motion.getX(),   motion.getY());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Rectangle getShape() {
        return shape;
    }

    public void setShape(Rectangle s){
        this.shape = s;
    }


}