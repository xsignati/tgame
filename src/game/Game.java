package game;

import java.lang.reflect.InvocationTargetException;

public interface Game {
    void update();
    void render() throws InterruptedException, InvocationTargetException;
}
