package components;

import entity.Entity;
import events.EventBus;

import java.util.Objects;

public class Motion extends Component {
    public int dx;
    public int dy;
    public int previousDy;
    Position position = entity.position;

    public Motion(Entity entity, EventBus eventBus) {
        super(entity, eventBus);
    }

    public void update(){
        moveEntity();
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

    boolean jumpFinished = true;
    int jumpLeft = 10;
    public void jump() {
        if(previousDy == 0 && jumpFinished) {
            jumpLeft = 20;
        }
        if(jumpLeft > 0){
            jumpLeft--;
            dy+=-25;
        }
        else{
            jumpFinished = true;
        }
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

    public void reverseDx() {
        dx *= -1;}

    public void zeroDy(){
        dy = 0;
    }

}