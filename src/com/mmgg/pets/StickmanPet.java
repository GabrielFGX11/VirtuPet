package com.mmgg.pets;

import com.mmgg.main.Main;

public class StickmanPet extends Pet{

	public StickmanPet(int x, int y, int width, int height, double minSpd, double maxSpd) {
		super(x, y, width, height, minSpd, maxSpd);
		loadSprites(9, Main.spritesStickman, 64, 64);
		movement = new PetMovement(this, x, y, "Plataform");
		maxFrames = 5;
	}

	@Override
	public void updateImage(boolean[] dir) {
		// TODO Auto-generated method stub
		boolean left = dir[0];
		boolean right = dir[1];
		
		if(left) {
			resizeAndSetIcon(spriteLeft[index]);
		}else if(right) {
			resizeAndSetIcon(spriteRight[index]);
		}
		
		// Detecção de movimento
		if(!movement.dir[movement.STOP]) {
			moved = false;
		}else {
			moved = right || left;
		}

	    if(moved) {
	        detectedMove();
	    }else {
	        index = 0; // Fica parado na primeira sprite
	    }
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		movement.tick();
	}

}
