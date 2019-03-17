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

	        

//	        this.entityManager.AddEntity(handler.newEnemy(Images.PEnemyIdle,handler,700, 2000,"InWorldState","Sergio","Cave","EnemyOne",150,25,80,1,8,12,20,10,20,10,1,10,"None","Thunder",null,null)); //lvl 2 difficulty
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
	    	
	    	 townWalls.add(new InWorldWalls(handler, 100, 0, 10, imageHeight, "Wall"));								// Left Border
	    	 townWalls.add(new InWorldWalls(handler, 100, 0, imageWidth, 10, "Wall"));								//Top Border
	    	 townWalls.add(new InWorldWalls(handler,100,  imageHeight, imageWidth, 10, "Wall"));					//Bottom Border
	    	 townWalls.add(new InWorldWalls(handler,imageWidth,0, 10, imageHeight, "Wall"));						// Right Border
	    	
	    	 townWalls.add(new InWorldWalls(handler,2400, 0, 10, 500, "Wall"));										// Entrance Walls
	    	 townWalls.add(new InWorldWalls(handler,3200,0, 10, 300, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,3200,300, 500, 10, "Wall"));
	    	 
	    	 townWalls.add(new InWorldWalls(handler,3700,300, 10, 300, "Wall"));									//Walls around the right entrance house
	    	 townWalls.add(new InWorldWalls(handler,3350,600, 600, 10, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,3350,600, 10, 900, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,3350,1500, 500, 10, "Wall"));
	    	 townWalls.add(new InWorldWalls(handler,3850,1500, 10, 150, "Wall"));
	    	 
	    	 
	    	 
		    	
		    	
//	    	 townWalls.add(new InWorldWalls(handler, 2440, 3355, 1, 500, "Wall"));									// Water Lake
//	         caveWalls.add(new InWorldWalls(handler, 1985, 3190, 500, 140, "Wall"));									//
//	         caveWalls.add(new InWorldWalls(handler, 1665, 3030, 500, 140, "Wall"));									//
//	         caveWalls.add(new InWorldWalls(handler, 1495, 2285, 1040, 700, "Wall"));								//
//	         caveWalls.add(new InWorldWalls(handler, 1595, 2985, 100, 100, "Wall"));									//
//	         caveWalls.add(new InWorldWalls(handler, 2520, 2750, 800, 1, "Wall"));									//
//	         caveWalls.add(new InWorldWalls(handler, 3258, 2608, 400, 400, "Wall"));									//
//
//	         caveWalls.add(new InWorldWalls(handler, 216, 500, 1030, 1000, "Wall"));									// Lava Lake
//	         caveWalls.add(new InWorldWalls(handler, 1246, 518, 300, 415, "Wall"));									//
//	         caveWalls.add(new InWorldWalls(handler, 222, 1428, 1010, 130, "Wall"));									//
//	         caveWalls.add(new InWorldWalls(handler, 184, 1640, 100, 100, "Wall"));									// Lava Hole
//
//
//
//	         caveWalls.add(new InWorldWalls(handler, 176, 140, 455, 345, "Wall"));									// TopLeft Side Wall with torch
//	         caveWalls.add(new InWorldWalls(handler, 661, 205, 120, 100, "Wall"));									// Hole next to TopLeft Side wall with torch
//
//
//	         caveWalls.add(new InWorldWalls(handler, 1940, 2130, 100, 100, "Wall"));									// Pond next to Statue
//	         caveWalls.add(new InWorldWalls(handler, 2066, 2050, 180, 125, "Wall"));                                  //Statue
//	         caveWalls.add(new InWorldWalls(handler, 3380, 510, 120, 100, "Wall"));									// Hole next to Exit
//	         caveWalls.add(new InWorldWalls(handler, 2744, 140, 200, 300, "Wall"));									// Left wall relative to Exit
//	         caveWalls.add(new InWorldWalls(handler, 3288, 140, 200, 300, "Wall"));									// Right wall relative to Exit
//
//	         caveWalls.add(new InWorldWalls(handler, imageWidth/3, imageHeight, 300, 50, "Wall"));					// Entrance
//
//	         caveWalls.add(new InWorldWalls(handler, 2950, 340, 320, 100, "Start Exit"));							// Exit at Start
//	         caveWalls.add(new InWorldWalls(handler, 1230, 3900, 280, 100, "End Exit"));							// Exit at End
//


	    	
	    }
	    @Override
	    public ArrayList<InWorldWalls> getWalls() {
	        return townWalls;
	    }
	    

}
