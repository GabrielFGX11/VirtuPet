package com.mmgg.pets;

import java.util.Random;

import com.mmgg.main.Main;

public class PetMovement{

	private int numDirX = 0, numDirY = 0, frames;
	private boolean[] dir = {false, false, false, false, false};
	private Random rand;
	private double spd = 2, gravity = 5;
	private int x, y;
	private String type;
	
	public PetMovement(int x, int y, String type) {
		this.x = x;
		this.y = y;
		this.type = type;
		rand =  new Random();
	}
	
	public void tick() {
		if(type.equals("Plataform")) {
			movePlataform();
		}else if(type.equals("TopDown")) {
			moveTopDown();
		}
		randomDir();
	}
	
	public int getDirX() {
		return numDirX;
	}

	public int getDirY() {
		return numDirY;
	}
	
	public void movePlataform() {
		if(y + Main.pet.getHeight() < Main.getScreenHeight()) {
			y+=gravity;
			dir[2] = true;
		}else if(dir[0] && !dir[2]) { //Left
			x-=spd;
			Main.pet.detectedMove();
		}else if(dir[1] && !dir[2]) { //Right
			x+=spd;
			Main.pet.detectedMove();
		}
		if (x < 0) {
		    x = 0;  // Bateu na borda esquerda
		}
		if (y < 0) {
		    y = 0;  // Bateu no topo da tela
		}
		if (x + Main.pet.getWidth() > Main.getScreenWidth()) {
		    x = Main.getScreenWidth() - Main.pet.getWidth();  // Bateu na borda direita
		}
		if (y + Main.pet.getHeight() > Main.getScreenHeight()) {
		    y = Main.getScreenHeight() - Main.pet.getHeight();  // Bateu na borda inferior
		}
		Main.pet.setLocation(x,y);
	}

	public void moveTopDown() {
		if(dir[0]) { //Left
			x-=spd;
		}else if(dir[1]) { //Right
			x+=spd;
		}
		if(dir[2]) { //Up
			y-=spd;
		}else if(dir[3]) { //Down
			y+=spd;
		}
		if (x < 0) {
		    x = 0;  // Bateu na borda esquerda
		}
		if (y < 0) {
		    y = 0;  // Bateu no topo da tela
		}
		if (x + Main.pet.getWidth() > Main.getScreenWidth()) {
		    x = Main.getScreenWidth() - Main.pet.getWidth();  // Bateu na borda direita
		}
		if (y + Main.pet.getHeight() > Main.getScreenHeight()) {
		    y = Main.getScreenHeight() - Main.pet.getHeight();  // Bateu na borda inferior
		}
		Main.pet.setLocation(x,y);
	}
	
	public void randomDir() {
			frames++;
			if(frames == 60) {
				frames = 0;
				dir[numDirX] = false;
				dir[numDirY] = false;
				numDirX  = rand.nextInt(2);
				numDirY  = rand.nextInt(3)+2;
				spd = spdCalculator();
			}
//			System.out.println(numDirX + "/"+ numDirY);
			if(type.equals("TopDown")) {
				Main.pet.updateImage(dir);
			}else if(type.equals("Plataform")) {
				Main.pet.updateImagePlataform(dir);
			}
			dir[numDirX] = true;
			dir[numDirY] = true;
	}
	
	private double spdCalculator() {
		if(rand.nextBoolean()) {
			return Main.pet.getMaxSpd();
		}else if(rand.nextBoolean()) {
			return Main.pet.getMinSpd();
		}else {
			return rand.nextDouble(Main.pet.getMaxSpd()+Main.pet.getMinSpd());
		}
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
