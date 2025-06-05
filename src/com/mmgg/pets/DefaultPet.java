package com.mmgg.pets;

import java.awt.MouseInfo;

import com.mmgg.main.Main;

public class DefaultPet extends Pet{
	
	public static boolean morph;
	
	public DefaultPet(int x, int y, int width, int height, double minSpd, double maxSpd) {
		super(x, y, width, height, minSpd, maxSpd);
		loadSprites(6, Main.spritesMorph, 180, 180);
		movement = new PetMovement(this, x, y, "TopDown");
		maxFrames = 8;
		morph = true;
	}
	
	@Override
	public void updateImage(boolean[] dir) {
		// TODO Auto-generated method stub
		boolean left = dir[0];
		boolean right = dir[1];
		boolean up = dir[2];
		boolean down = dir[3];
		
		if(left) {
			resizeAndSetIcon(spriteLeft[index]);
		}else if(right) {
			resizeAndSetIcon(spriteRight[index]);
		}else if(up) {
			resizeAndSetIcon(spriteUp[index]);
		}else if(down) {
			resizeAndSetIcon(spriteDown[index]);
		}
		
		// Detecção de movimento
	    moved = right || left || up || down;

	    if (moved) {
	        detectedMove();
	    } else {
	        index = 0; // Fica parado na primeira sprite
	    }
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		movement.tick();
		mouseLoc = MouseInfo.getPointerInfo().getLocation();
	}

}
