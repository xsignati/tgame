package levels;

import components.*;
import entity.Entity;
import game.GameManager;
import graphics.Renderer;
import config.ScreenSizes;
import events.EventBus;
import input.GameController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class LevelCreator<levelEntities> {

    private static EventBus eventBus = new EventBus();
    public static List<Entity> levelEntities = new LinkedList<>();

    public Renderer view;
    public static final int[][] a =
    {{5,0,0,6,0,0,0,0,0,5,5,5,0,5,5,0,6,0,0,0,0,0,6,0,0,0,0,0,0,6,0,0,0,0,0,6,0,0,0,0,0,6,0,0,0,7,0,0,7,6,0,0,7,0,0,6,0,0,0,0,0,0,6,0,0,7,6,0,0,7,0,0,6,0,0,6,0,0,0,0,0,6,0,0,0,0,0,0,6,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
     {5,0,0,6,0,0,0,2,0,0,0,0,0,5,5,0,7,0,0,0,6,0,0,0,0,5,5,5,0,0,7,0,0,7,2,0,0,6,0,0,0,0,0,6,0,0,0,7,0,0,7,0,0,0,0,0,0,0,0,2,6,0,0,0,0,0,6,0,0,0,7,0,0,7,0,2,0,6,0,0,0,0,0,6,0,0,0,7,0,0,7,0,0,0,0,6,0,0,0,0,0,6,0,0,0,7,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
     {5,6,0,0,6,0,7,6,0,0,6,0,0,5,5,5,5,5,5,0,0,0,2,0,5,5,5,5,5,0,0,0,0,0,0,0,0,0,0,0,0,2,0,2,0,0,5,5,0,0,5,5,0,0,0,5,5,5,5,5,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
     {5,0,1,5,5,0,0,0,0,5,5,5,5,5,5,5,5,5,0,0,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,0,0,5,5,5,5,5,5,5,5,0,5,5,0,0,5,5,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
     {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,0,0,0,0,5,0,5,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,5,0,5,5,0,0,5,5,0,0,0,0,0,0,0,0,0,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5}};
    public static int LEVEL_LENGTH = a[0].length * ScreenSizes.EVERY_ENTITY_SIZE;
    public Entity player = new Entity("player");
    public void createPlayer(int x, int y){

        Motion motion = new Motion(player, eventBus);
        player.addComponent(motion);

        ///Camera
         createPlayerCamera();
        player.position.x = x;
        player.position.y = y;
        Sprite s = new Sprite(player, eventBus, Color.RED, new Rectangle(x, y, ScreenSizes.EVERY_ENTITY_SIZE, ScreenSizes.EVERY_ENTITY_SIZE));
        s.priority = 1;
        Collider collider = new Collider(player,eventBus,motion,ScreenSizes.EVERY_ENTITY_SIZE,ScreenSizes.EVERY_ENTITY_SIZE);
        player.addComponent(collider);
        player.addComponent(s);
        player.addComponent(new Gravity(player, eventBus, motion));
        PlayerBehavior playerBehavior = new PlayerBehavior(player, eventBus);
        player.addComponent(playerBehavior);



        //collidor2.addComponent(new Collider(collidor2, eventBus));
        levelEntities.add(player);
    }


    public void createEnemy(int x, int y, int num){
        Entity enemy = new Entity("enemy");
        Motion motion = new Motion(enemy, eventBus);

        enemy.addComponent(motion);
        enemy.position.x = x;
        enemy.position.y = y;
        Sprite s = new Sprite(enemy, eventBus, Color.YELLOW, new Rectangle(x, y, ScreenSizes.EVERY_ENTITY_SIZE, ScreenSizes.EVERY_ENTITY_SIZE));
        s.priority = 1;
        Collider collider = new Collider(enemy,eventBus,motion,ScreenSizes.EVERY_ENTITY_SIZE,ScreenSizes.EVERY_ENTITY_SIZE);
        enemy.addComponent(collider);
        enemy.addComponent(s);
        enemy.addComponent(new Gravity(enemy, eventBus, motion));
        enemy.addComponent(new EnemyBehavior(enemy, eventBus));

        levelEntities.add(enemy);
    }

    Entity playerCamera = new Entity("playerCamera");
    public void createPlayerCamera(){
        //camera
        Camera camera = new Camera(player, eventBus, new Position(0,0));
        GameManager.camera = camera;
        player.addComponent(camera);
    }

    public void createFloor(int x, int y){
        Entity collidor = new Entity("floor");
        collidor.position.x = x;
        collidor.position.y = y;
        Motion motion = new Motion(collidor, eventBus);
        Collider collider = new Collider(collidor,eventBus,motion,ScreenSizes.EVERY_ENTITY_SIZE,ScreenSizes.EVERY_ENTITY_SIZE);
        collidor.addComponent(collider);
        collidor.addComponent(motion);
        int rgb = 1 + new Random().nextInt(5) * 10;
        collidor.addComponent(new Sprite(collidor, eventBus, new Color(rgb,rgb,rgb), new Rectangle(x, y, ScreenSizes.EVERY_ENTITY_SIZE, ScreenSizes.EVERY_ENTITY_SIZE)));
        levelEntities.add(collidor);
    }

    public void createBackground(int x, int y){
        Entity background = new Entity("background");
        background.position.x = x;
        background.position.y = y;

        Motion motion = new Motion(background, eventBus);
        background.addComponent(motion);
        int rgb = 210 + new Random().nextInt(5) * 10;
        Color color = new Color(rgb,rgb,rgb);
        background.addComponent(new Sprite(background, eventBus, color, new Rectangle(x, y, ScreenSizes.EVERY_ENTITY_SIZE, ScreenSizes.EVERY_ENTITY_SIZE)));
        levelEntities.add(background);
    }

    public void create(){
        for(int i = 0 ; i < a.length ; i++){
            for(int j = 0 ; j < a[i].length ; j++){
                if(a[i][j] == 1){
                    createPlayer(j * ScreenSizes.EVERY_ENTITY_SIZE, i * ScreenSizes.EVERY_ENTITY_SIZE);
                }
                if(a[i][j] == 2){
                    createEnemy(j * ScreenSizes.EVERY_ENTITY_SIZE, i * ScreenSizes.EVERY_ENTITY_SIZE,i+j);
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
        createLevelManager();
    }

    public static void createProjectile(int x, int y, String order){
        Entity projectile = new Entity("projectile");
        projectile.active = true;
        projectile.position.x = x;
        projectile.position.y = y;
        Motion motion = new Motion(projectile, eventBus);
        Collider collider = new Collider(projectile,eventBus,motion,ScreenSizes.EVERY_ENTITY_SIZE,ScreenSizes.EVERY_ENTITY_SIZE);
        projectile.addComponent(collider);
        projectile.addComponent(motion);
        projectile.addComponent(new Sprite(projectile, eventBus, Color.ORANGE, new Rectangle(x, y, ScreenSizes.EVERY_ENTITY_SIZE, ScreenSizes.EVERY_ENTITY_SIZE)));
        ProjectileBehavior projectileBehavior = new ProjectileBehavior(projectile,eventBus, order);
        projectile.addComponent(projectileBehavior);
        levelEntities.add(projectile);
    }
    Entity levelManager = new Entity("levelManager");
    public void createLevelManager(){
        Motion motion = new Motion(levelManager, eventBus);
        Collider collider = new Collider(levelManager,eventBus,motion,ScreenSizes.EVERY_ENTITY_SIZE,ScreenSizes.EVERY_ENTITY_SIZE);
        LevelBehavior levelBehavior = new LevelBehavior(levelManager,eventBus,player);
        levelManager.addComponent(collider);
        levelManager.addComponent(motion);
        levelManager.addComponent(levelBehavior);
        levelEntities.add(levelManager);
    }

}

