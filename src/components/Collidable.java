package components;

import entity.Entity;

public interface Collidable {
    void onCollision(Entity other, boolean[] isColliding);
}
