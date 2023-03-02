package components;

import entities.Entity;

public interface Collidable {
    void onCollision(Entity other, boolean[] isColliding);
}
