package components;

import entity.Entity;
import events.EventBus;

import java.awt.*;

public class Sprite extends Component {
    public  Color color;
    private final Rectangle shape;
    public int priority = 0;

    public Sprite(Entity entity, EventBus eventBus, Color color, Rectangle shape) {
        super(entity, eventBus);
        this.color = color;
        this.shape = shape;
    }

    public void update() {
        shape.setLocation( entity.position.x, entity.position.y);
    }

    public Color getColor() {
        return color;
    }

    public Rectangle getShape() {
        return shape;
    }

    public Sprite getCopyOfMovedSprite(int amount){
        Rectangle newShape = new Rectangle(shape.x - amount,shape.y, shape.width, shape.height);
        Sprite newSprite =  new Sprite(entity,eventBus, color, newShape);
        newSprite.priority = priority;
        return newSprite;
    }

}