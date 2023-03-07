package components;

import entity.Entity;
import events.EventBus;
import events.MotionEvent;


public class Motion extends Component {
    Vector2d position = new Vector2d(0,0);
    Vector2d velocity = new Vector2d(0,0);
    int x;
    int dx;
    int y;
    int dy;
    int width;
    int height;
    int speed;
    public int previousDy;

    public Motion(Entity entity, EventBus eventBus, int speed, int x, int dx, int y, int dy, int width, int height) {
        super(entity, eventBus);
        this.x = x;
        this.dx = dx;
        this.y = y;
        this.dy = dy;
        this.width = width;
        this.height = height;
        this.speed = speed;
        super.eventBus.register(MotionEvent.class, this::onEvent);
    }

    public void onEvent(MotionEvent motionEvent){
        if(motionEvent.e.equals(entity)) {
            dy += motionEvent.y;
            dx += motionEvent.x;
        }
    }

    public void update(){
        x+=dx;
        y+=dy;
        previousDy = dy;
        dx = dy = 0;
    }

    public void up(){
        dy+=-speed - 10;
    }

    public void down(){
        dy+=speed;
    }

    public void left(){
        dx+=-speed;
    }

    public void right(){
        dx+=speed+10;
    }

    public void jump() {
        if(previousDy == 0) {
            dy+=-speed * 35;
        }
    }


    public enum TYPE{
        UP,DOWN,RIGHT,LEFT
    }

    public void move(Vector2d velocity){
        move(velocity, speed);
    }

    public void move(Vector2d velocity, int amount){
        position.x += velocity.x;
        position.y += velocity.y;
    }

    public int getX() {
        return x;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getY() {
        return y;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}