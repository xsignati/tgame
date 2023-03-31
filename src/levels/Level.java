package levels;

import components.*;
import entity.Entity;
import game.GameManager;
import graphics.Renderer;
import config.ScreenSizes;
import events.EventBus;
import input.GameController;
import world.GameWorld;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class Level<levelEntities> {
    private EventBus eventBus = new EventBus();
    public static java.util.List<Entity> levelEntities = new LinkedList<>();

    public Renderer view;
    public static final int[][] a =
    {{5,0,0,6,0,0,0,0,0,5,5,5,0,0,0,0,6,0,0,0,0,0,6,0,0,0,0,0,0,6,0,0,0,0,0,6,0,0,0,0,0,6,0,0,0,7,0,0,7,6,0,0,7,0,0,6,0,0,0,0,0,0,6,0,0,7,6,0,0,7,0,0,6,0,0,6,0,0,0,0,0,6,0,0,0,0,0,0,6,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
     {5,0,0,6,0,0,0,0,0,0,0,0,0,7,0,0,7,0,0,0,6,0,0,0,0,5,5,5,0,0,7,0,0,7,0,0,0,6,0,0,0,0,0,6,0,0,0,7,0,0,7,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,6,0,0,0,7,0,0,7,0,0,0,6,0,0,0,0,0,6,0,0,0,7,0,0,7,0,0,0,0,6,0,0,0,0,0,6,0,0,0,7,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
     {5,6,0,0,6,0,7,6,0,0,6,0,0,0,5,5,5,5,5,0,0,0,0,0,5,5,5,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,0,0,5,5,0,0,0,5,5,5,5,5,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
     {5,0,1,5,5,0,0,0,0,5,5,5,5,5,5,5,5,5,0,0,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,0,0,5,5,5,5,5,5,5,5,0,5,5,0,0,5,5,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
     {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,0,0,0,0,5,0,5,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,5,0,5,5,0,0,5,5,0,0,0,0,0,0,0,0,0,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5}};

    public Entity player = new Entity("player");
    public void createPlayer(int x, int y){

        Motion motion = new Motion(player, eventBus,Renderer.BASIC_UNIT_SIZE,x,0,y,0,ScreenSizes.EVERY_ENTITY_SIZE,ScreenSizes.EVERY_ENTITY_SIZE);
        player.addComponent(motion);

        ///Camera
         createPlayerCamera();

        Sprite s = new Sprite(player, eventBus, Color.RED, new Rectangle(x, y, ScreenSizes.EVERY_ENTITY_SIZE, ScreenSizes.EVERY_ENTITY_SIZE),motion);
        s.priority = 1;
        player.addComponent(s);
        player.addComponent(new Gravity(player, eventBus, motion));
        player.addComponent(new PlayerBehavior(player, eventBus));
        //controller
        GameController controller = new GameController(player, eventBus);
        controller.addKey(KeyEvent.VK_W, motion::up);
        controller.addKey(KeyEvent.VK_S, motion::down);
        controller.addKey(KeyEvent.VK_A, motion::left);
        controller.addKey(KeyEvent.VK_D, motion::right);
        controller.addKey(KeyEvent.VK_SPACE, motion::jump);

        player.addComponent(controller);
        view.attachListener(controller.inputController);
        //collidor2.addComponent(new Collider(collidor2, eventBus));
        levelEntities.add(player);
    }

    Entity playerCamera = new Entity("playerCamera");
    public void createPlayerCamera(){
        //camera
        Camera camera = new Camera(player, eventBus,   player);
        GameManager.camera = camera;
        player.addComponent(camera);
    }

    public void createFloor(int x, int y){
        Entity collidor = new Entity("floor");
        Motion motion = new Motion(collidor, eventBus, Renderer.BASIC_UNIT_SIZE,x,0,y,0,ScreenSizes.EVERY_ENTITY_SIZE,ScreenSizes.EVERY_ENTITY_SIZE);
        collidor.addComponent(motion);
        int rgb = 1 + new Random().nextInt(5) * 10;
        collidor.addComponent(new Sprite(collidor, eventBus, new Color(rgb,rgb,rgb), new Rectangle(x, y, ScreenSizes.EVERY_ENTITY_SIZE, ScreenSizes.EVERY_ENTITY_SIZE),motion));
        levelEntities.add(collidor);
    }

    public void createBackground(int x, int y){
        Entity background = new Entity("background");
        background.collidable = true;
        Motion motion = new Motion(background, eventBus, Renderer.BASIC_UNIT_SIZE,x,0,y,0,ScreenSizes.EVERY_ENTITY_SIZE,ScreenSizes.EVERY_ENTITY_SIZE);
        background.addComponent(motion);
        int rgb = 210 + new Random().nextInt(5) * 10;
        Color color = new Color(rgb,rgb,rgb);
        background.addComponent(new Sprite(background, eventBus, color, new Rectangle(x, y, ScreenSizes.EVERY_ENTITY_SIZE, ScreenSizes.EVERY_ENTITY_SIZE),motion));
        levelEntities.add(background);
    }

    public void create(){
        for(int i = 0 ; i < a.length ; i++){
            for(int j = 0 ; j < a[i].length ; j++){
                if(a[i][j] == 1){
                    createPlayer(j * ScreenSizes.EVERY_ENTITY_SIZE, i * ScreenSizes.EVERY_ENTITY_SIZE);
                }

                if(a[i][j] == 5){
                    createFloor(j * ScreenSizes.EVERY_ENTITY_SIZE, i * ScreenSizes.EVERY_ENTITY_SIZE);
                }

                if(a[i][j] == 0 || a[i][j] == 7 || a[i][j] == 6){
                    createBackground(j * ScreenSizes.EVERY_ENTITY_SIZE, i * ScreenSizes.EVERY_ENTITY_SIZE);
                }

            }
        }
        //GameManager.entities = new LinkedList<>(levelEntities);
    }

}

