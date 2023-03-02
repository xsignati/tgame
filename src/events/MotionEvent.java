package events;

import entities.Entity;

public class MotionEvent {
    public int y = 0;
    public int x = 0;
    public Entity e;

    public MotionEvent(Entity e, int x, int y) {
        this.e = e;
        this.y = y;
        this.x = x;
    }
}
