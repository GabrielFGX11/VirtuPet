package com.mmgg.pets;

import com.mmgg.main.Main;

public class LizardPet extends Pet{
	
	public static boolean lizard;

	public LizardPet(int x, int y, int width, int height, double minSpd, double maxSpd) {
		super(x, y, width, height, minSpd, maxSpd);
		loadSprites(1, Main.spritesLizard, 200, 200);
		loadSpritesD(1, Main.spritesLizardD, 200, 200);
		movement = new PetMovement(this, x, y, "TopDown");
		maxFrames = 5;
		lizard = true;
	}

	@Override
	public void updateImage(boolean[] dir) {
		// TODO Auto-generated method stub
		boolean left = dir[0];
		boolean right = dir[1];
		boolean up = dir[2];
		boolean down = dir[3];
		boolean leftUp = dir[0] && dir[2];
		boolean rightUp = dir[1] && dir[2];
		boolean leftDown = dir[0] && dir[3];
		boolean rightDown = dir[1] && dir[3];
		
		if(leftUp) {
			resizeAndSetIcon(spriteLeft_Up[index]);			
		}else if(rightUp) {
			resizeAndSetIcon(spriteRight_Up[index]);			
		}else if(leftDown) {
			resizeAndSetIcon(spriteLeft_Down[index]);			
		}else if(rightDown) {
			resizeAndSetIcon(spriteRight_Down[index]);			
		}else
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
	    moved = right || left || up || down || leftUp || rightUp || leftDown || rightDown;

	    if(moved) {
	        detectedMove();
	    }else {
	        index = 0; // Fica parado na primeira sprite
	    }
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		movement.seguirMouse = false;
		movement.tick();
	}

}
