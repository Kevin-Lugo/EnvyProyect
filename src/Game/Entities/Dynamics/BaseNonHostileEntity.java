package Game.Entities.Dynamics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.GameStates.InWorldState;
import Game.World.InWorldAreas.InWorldWalls;
import Game.World.Walls;
import Main.GameSetUp;
import Main.Handler;

public class BaseNonHostileEntity extends BaseDynamicEntity {
	
	private Random rand;
	private boolean PlayerDetected;
	private Rectangle detector;
	
	private boolean BeingTalkedTo;
	private int count;
	private int directionMov;
	double chaseSpeed = 1.5;
	boolean canMove = true;
	public String foundState;
	public String name="friendly";
	public String Area;//None for MapState
    public String type;//class it is ex: "EnemyOne"
   
    
    public BaseNonHostileEntity(Handler handler, int xPosition, int yPosition, String state,String name,String area, BufferedImage[] animFrames) {
		super(handler, xPosition, yPosition,animFrames);
		this.foundState = state;
		PlayerDetected = false;
		Area=area;
		count = 0;
		directionMov = 4;
		this.name = name;
		nextArea = new Rectangle();
		rand = new Random();
		detector = new Rectangle();
		BeingTalkedTo = false;
		
		  }
    

	@Override
	public void tick() {
			super.tick();
			if(handler.getArea().equals(this.Area)) {
	            UpdateNextMove();
	            checkCollision();
	
	
	            if (canMove) {
	                count++;
	                if (count >= 100 + rand.nextInt(350)) {
	
	                    directionMov = rand.nextInt(5); // 0 (idle), 1(up), 2(down), 3(left), 4(right)
	
	                    count = 0;
	                }
	
	                PlayerDetector();
	                getBeingTalkedTo();
	               
	                	Move();
	               
	            }
	            canMove = true;
	        }
	}

    private void checkCollision() {
	    if(foundState.equals("MapState")){
            for(Walls w:handler.getWorldManager().getWalls()){
                if(w.intersects(nextArea)) {
                    canMove = false;
                    switch (directionMov) {
                        case 0://idle
                            break;
                        case 1://down
                            this.setYOffset(this.getYOffset() - speed);
                            break;
                        case 2://up
                            this.setYOffset(this.getYOffset() + speed);
                            break;

                        case 3://left
                            this.setXOffset(this.getXOffset() + speed);
                            break;

                        case 4://right
                            this.setXOffset(this.getXOffset() - speed);
                            break;
                    }
                }
            }
        }else if(foundState.equals("InWorldState")){
            for(InWorldWalls w:InWorldState.currentArea.getWalls()){
                if(w.intersects(nextArea)) {
                    canMove = false;
                    switch (directionMov) {
                        case 1://down
                            this.setYOffset(this.getYOffset() - speed);
                            break;
                        case 2://up
                            this.setYOffset(this.getYOffset() + speed);
                            break;

                        case 3://left
                            this.setXOffset(this.getXOffset() + speed);
                            break;

                        case 4://right
                            this.setXOffset(this.getXOffset() - speed);
                            break;
                    }
                }
            }
        }
    }

    private void UpdateNextMove() {
		if(foundState.equals("MapState")) {
			switch (facing) {
				case "Up":
					nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement(), (int) getYOffset() + handler.getYDisplacement() - 10, getCollision().width, getCollision().height / 2);

					break;
				case "Down":
					nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement(), (int) getYOffset() + handler.getYDisplacement() + getCollision().height, getCollision().width, 10);

					break;
				case "Left":
					nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement() - 10, (int) getYOffset() + handler.getYDisplacement(), 10, getCollision().height);

					break;
				case "Right":
					nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement() + getCollision().width, (int) getYOffset() + handler.getYDisplacement(), 10, getCollision().height);

					break;
			}
		}else if(foundState.equals("InWorldState")){
			switch (facing) {
				case "Up":
					nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement(), (int) getYOffset() + handler.getYInWorldDisplacement() - 10, getCollision().width, getCollision().height / 2);

					break;
				case "Down":
					nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement(), (int) getYOffset() + handler.getYInWorldDisplacement() + getCollision().height, getCollision().width, 10);

					break;
				case "Left":
					nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement() - 10, (int) getYOffset() + handler.getYInWorldDisplacement(), 10, getCollision().height);

					break;
				case "Right":
					nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement() + getCollision().width, (int) getYOffset() + handler.getYInWorldDisplacement(), 10, getCollision().height);

					break;
			}
		}
	}

	private void PlayerDetector() {

		detector = this.getCollision();

		detector.setRect(detector.getX() - detector.getWidth() * 2, detector.getY() - detector.getHeight() * 2,
				detector.getWidth()*5 , detector.getHeight()*5 );

		this.PlayerDetected = handler.getEntityManager().getPlayer().getCollision().intersects(detector);

		if (!Player.checkInWorld) {
			chaseSpeed = 1.5;
		}
		else {
			chaseSpeed = 5;
		}
	}

	@Override
	public void render(Graphics g) {
		if (this.PlayerDetected) {
			Graphics2D g3 = (Graphics2D) g;
			g3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			g3.setColor(Color.WHITE);
			g3.drawString("Press E, To Interact" , 500, 100);
			
			
		}
		
		if (GameSetUp.DEBUGMODE) {
			Graphics2D g2 = (Graphics2D) g;
			g2.draw(detector);
			g2.draw(nextArea);
		}

	}


	private void Move() {

		switch (directionMov) {
			case 0:
				break;
			case 1:
				facing = "Down";
				this.setYOffset(this.getYOffset() + speed);
				break;

			case 2:
				facing = "Up";
				this.setYOffset(this.getYOffset() - speed);
				break;

			case 3:
				facing = "Left";
				this.setXOffset(this.getXOffset() - speed);
				break;

			case 4:
				facing = "Right";
				this.setXOffset(this.getXOffset() + speed);
				break;
		}
	}
	public boolean getBeingTalkedTo() {
		 if(PlayerDetected ) {
         	
         	if( handler.getKeyManager().attbut) {
         		
         	this.BeingTalkedTo = true;
         	handler.getEntityManager().getPlayer().QuestAssigned = true;
         	
         }else
         	this.BeingTalkedTo = false;
         }
		return this.BeingTalkedTo;
	}
    
    
    
    
    
    
    

}
