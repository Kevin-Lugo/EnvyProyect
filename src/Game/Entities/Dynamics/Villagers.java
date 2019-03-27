package Game.Entities.Dynamics;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Game.GameStates.FightState;
import Game.GameStates.State;
import Main.Handler;
import Resources.Images;

public class Villagers extends BaseNonHostileEntity{ 
	
	
    Rectangle villager;
    int width, height;
    private String VillagerMessage = "Defeat Bad Bunny and save our town from danger.";  
    
    public Villagers(Handler handler, int xPosition, int yPosition, String state, String name, String area, BufferedImage[] animFrames) {
        super(handler, yPosition, yPosition,state,name,area,animFrames);
        width = 30;
        height = 30;
        speed = 1;
        type="Villager";
        this.setXOffset(xPosition);
        this.setYOffset(yPosition);

        this.foundState = state;
        villager = new Rectangle();
    }
    
    @Override
    public void tick() {

        if(!Player.isinArea)super.tick();
       
        if (handler.getEntityManager().getPlayer().QuestFinished && super.getBeingTalkedTo()) {
        	handler.getEntityManager().getPlayer().setSkill("Ice");
        }
        
        

    }
    
	@Override
	public void render(Graphics g) {
		super.render(g);

		Graphics2D g2 = (Graphics2D) g;
		Graphics2D g3 = (Graphics2D) g;

		if (handler.getArea().equals(this.Area)) {
			if (!Player.checkInWorld) {
				villager = new Rectangle((int) (handler.getXDisplacement() + getXOffset()),
						(int) (handler.getYDisplacement() + getYOffset()), 200, 100);

			} else {
				villager = new Rectangle((int) (handler.getXInWorldDisplacement() + getXOffset()),
						(int) (handler.getYInWorldDisplacement() + getYOffset()), 200, 200);
			}

			g2.setColor(Color.black);

			g.drawImage(Images.Thanos, villager.x, villager.y, villager.width, villager.height, null);

			g3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			g3.setColor(Color.WHITE);

			if (super.getBeingTalkedTo()) {
				if (handler.getEntityManager().getPlayer().QuestFinished) {
					g3.drawString("You have completed the quest!", 500, 150);

				} else {
					g3.drawString(this.VillagerMessage, 500, 150);
				}

			}
		}

	}
    
    @Override
    public Rectangle getCollision() {
        return villager;
    }
   
    
    
	

}
