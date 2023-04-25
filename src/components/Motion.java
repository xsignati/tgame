package components;

import entity.Entity;
import events.EventBus;

import java.util.Objects;

public class Motion extends Component {
    public int dx;
    public int dy;
    public int previousDx;
    public int previousDy;
    Position position = entity.position;

    public Motion(Entity entity, EventBus eventBus) {
        super(entity, eventBus);
    }

    public void update(){
        moveEntity();
        previousDx = dx;
        previousDy = dy;
        zeroDx();
        zeroDy();
    }

    private void moveEntity(){
        position.x+=dx;
        position.y+=dy;
    }

    public void up(){
        dy-= 15;
    }

    public void down(){
        dy+=10;
    }

    public void left(){
        dx+=-10;
    }

    public void right(){
        dx+=10;
    }





    public void accelerateDx(int amount){
        dx += amount;
    }

    public void accelerateDy(int amount){
        dy += amount;
    }

    public void zeroDx(){
        dx = 0;
    }

    public void zeroDy(){
        dy = 0;
    }

}