package Game.World.InWorldAreas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.*;

import Game.Entities.EntityManager;

import Game.World.Walls;
import Main.GameSetUp;
import Main.Handler;
import Resources.Images;

public class TownArea extends BaseArea {
	 	Rectangle exit;
	    Rectangle playerRect;
	    public static boolean isInTown = false;

	    private int imageWidth = 556 * 10, imageHeight = 428 * 10;

	    public final static int playerXSpawn = -2100, playerYSpawn = 100;

	    private Rectangle background = new Rectangle(3000, 3000);
	    public static ArrayList<InWorldWalls> townWalls;
	    
	    public TownArea(Handler handler, EntityManager entityManager) {
	    	
	        super(handler, entityManager);
	        name="Town";
	        handler.setXInWorldDisplacement(playerXSpawn);
	        handler.setYInWorldDisplacement(playerYSpawn);

	        playerRect = new Rectangle((int) handler.getWidth() /4 , (int)handler.getHeight()/ 4 , 70, 70);

	        this.entityManager = entityManager;

	        
	        this.entityManager.AddEntity(handler.newVillager(Images.PEnemyIdle, handler, 2500, 100, "InWorldState", "Villager", "Town", "Villagers"));	
	        this.entityManager.AddEntity(handler.newEnemy(Images.EnemyS,handler,2500, 2000,"InWorldState","Lord Shaggy","Town","EnemyOne",130,100,100,3,8,15,30,10,20,10,1,10,"None","Thunder",null,null)); //lvl 3 difficulty
//	        this.entityManager.AddEntity(handler.newEnemy(Images.PEnemyIdle,handler,3000, 1000,"InWorldState","Cave Dweller","Cave","EnemyOne",100,25,60,10,1,12,20,10,20,13,1,10,"None","Thunder",null,null)); // lvl 1 difficulty

//	        this.entityManager.AddEntity(new LightStatue (handler, 2080, 1770));
	        
	        townWalls = new ArrayList<>();
	        AddWalls();

	    }
	    
	    public void tick() {
	        super.tick();

	        for (Walls w : townWalls) {
	            w.tick();
	        }
	        if(!GameSetUp.LOADING) {
	            entityManager.tick();
	        }

	    }
	    
	    @Override
	    public void render(Graphics g) {
	        super.render(g);


	        Graphics2D g2 = (Graphics2D) g;

	        g2.setColor(Color.GREEN);
	        g2.fill(background);

	        g.drawImage(Images.ScaledTown, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

	        if (GameSetUp.DEBUGMODE) {
	            for (Walls w : townWalls) {

	                if (w.getType().equals("Wall"))
	                    g2.setColor(Color.black);
	                else
	                    g2.setColor(Color.PINK);

	                w.render(g2); 
	            }
	        }


	        entityManager.render(g);
	    }
	    
	    
	    
	    
	    public void AddWalls() {
	    	
	    	//InWorldWalls( handler, Xpos, Ypos, Width, Height, WallType)
	    	
	    	
	    	
	    	 townWalls.add(new InWorldWalls(handler, 2500, 0, 500, 100, "Start Town exit"));	
	    	 
	    	 
	    	 townWalls.add(new InWorldWalls(handler, 100, 0, 10, imageHeight, "Wall"));								// Left Border
	    	 townWalls.add(new InWorldWalls(handler, 100, 0, imageWidth, 10, "Wall"));								//Top Border
	    	 townWalls.add(new InWorldWalls(handler,100,  imageHeight, imageWidth, 10, "Wall"));					//Bottom Border
	    	 townWalls.add(new InWorldWalls(handler,imageWidth,0, 10, imageHeight, "Wall"));						// Right Border
	    	
	    	 townWalls.add(new InWorldWalls(handler,2400, 0, 10, 550, "Wall"));										// Entrance Walls
	    	 townWalls.add(new InWorldWalls(handler,3200,0, 10, 300, "Wall"));
	    
	    	 townWalls.add(new InWorldWalls(handler,3200,300, 500, 10, "Wall"));									// Tree walls around the right house
	    	 townWalls.add(new InWorldWalls(handler,3700, 400 , 700,10 , "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,4400,400, 10, 2200, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,4150,2600, 250, 10, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,4150,2600, 10, 700, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,3800,3300, 10, 300, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,3800,3300, 300, 10, "Wall"));
	    	 
	    	 townWalls.add(new InWorldWalls(handler,1450,3600, 2400, 10, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,1450,3300, 10, 300, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,1450,3250, 500, 50, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,1150,3250, 300, 10, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,1150,850, 10, 2400, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,1150,850, 300, 10, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,1450,550, 975, 10, "Wall"));
	    	
	    	 
	    	 townWalls.add(new InWorldWalls(handler,1450,550, 10, 1200, "Wall"));			// Upper Left House Rail Walls
	    	 townWalls.add(new InWorldWalls(handler,1450,1650, 400, 100, "Wall"));		
	    	 
	    	 
	    	 
	    	 
	    	 townWalls.add(new InWorldWalls(handler,1450,550, 800, 800, "Wall")); // Upper Left House Walls
	    	 
	    	 townWalls.add(new InWorldWalls(handler,1300,2450, 1200, 600, "Wall")); // Lower left House walls
	    	 townWalls.add(new InWorldWalls(handler,1750,2150, 750, 600, "Wall"));
	    	 
	    	 
	    	 
	    	 
	    	 townWalls.add(new InWorldWalls(handler,3700,300, 10, 300, "Wall"));									//Walls around the right entrance house
	    	 townWalls.add(new InWorldWalls(handler,3350,600, 750, 10, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,3350,600, 10, 900, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,4100,600, 10, 900, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,3350,1500, 750, 10, "Wall"));
	    	 
	    	 townWalls.add(new InWorldWalls(handler,4100,1500, 10, 300, "Wall"));
	    	 
	    	
	    	 
	    	 townWalls.add(new InWorldWalls(handler,3600,1700,500, 50, "Wall")); // Rail Walls
	    	 townWalls.add(new InWorldWalls(handler,3050,2100,900, 100, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,3200,2750,600, 100, "Wall"));
	    	 
	    	 
	    	 
//	         caveWalls.add(new InWorldWalls(handler, 2950, 340, 320, 100, "Start Exit"));							// Exit at Start
//	         caveWalls.add(new InWorldWalls(handler, 1230, 3900, 280, 100, "End Exit"));							// Exit at End
//


	    	
	    }
	    @Override
	    public ArrayList<InWorldWalls> getWalls() {
	        return townWalls;
	    }
	    

}
