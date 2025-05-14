package com.mmgg.main;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;

import com.mmgg.graphics.Spritesheet;
import com.mmgg.pets.Pet;

public class Main {
	
	public static Pet pet;
	private boolean running;
	protected static Spritesheet spritesLizard;
	public static Spritesheet spritesStickmanR;
	public static Spritesheet spritesStickmanL;
	
	private static int screenWidth, screenHeight;
	
	public Main() {
		spritesLizard = new Spritesheet("/res/spritesLizard.png");
		spritesStickmanR = new Spritesheet("/res/thickRunSheetR.png");
		spritesStickmanL = new Spritesheet("/res/thickRunSheetL.png");
		pet = new Pet(1000, 400, 100, 100, 1, 3);
		pet.setVisible(true);
		//Dimensão do tamanho da tela
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gd.getDefaultConfiguration());
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height - screenInsets.bottom;
	}
	
	public static int getScreenWidth() {
		return screenWidth;
	}

	public static int getScreenHeight() {
		return screenHeight;
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	    
	    // Inicia o loop em uma thread separada
	    new Thread(main::start).start();
	}

	public void start() {
	    running = true;  // Habilita o loop
	    loop();
	}

	public void loop() {
	    while (running) {
	        long start = System.nanoTime();
	        
	        tick();
	        
	        long delta = (System.nanoTime() - start) / 1000000;
	        long wait = Math.max(16 - delta, 0);  // Alvo de 60 FPS
	        try {
	            Thread.sleep(wait);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void tick() {
		pet.tick();
	}

}
