package com.mmgg.main;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.Toolkit;

import com.mmgg.graphics.Spritesheet;
import com.mmgg.pets.DefaultPet;
import com.mmgg.pets.Pet;

public class Main {
	
	public static Pet pet;
	private boolean running;
	public static Spritesheet spritesLizard;
	public static Spritesheet spritesLizardD;
	public static Spritesheet spritesStickman;
	public static Spritesheet spritesMorph;
	public static Robot robot;
	
	private static int screenWidth, screenHeight;
	
	public Main() {
		spritesLizard = new Spritesheet("/lizard.png");
		spritesLizardD = new Spritesheet("/lizardDiagonal.png");
		spritesStickman = new Spritesheet("/stickman.png");
		spritesMorph = new Spritesheet("/morph.png");
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pet = new DefaultPet(1366, 764, 300, 300, 1, 3);
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
	
	public static void trocarPet(Pet novoPet) {
	    // Esconde ou remove o pet atual
	    if (pet != null) {
	        pet.setVisible(false);
	    }

	    // Adiciona e mostra o novo pet
	    pet = (Pet) novoPet;	
	    pet.setVisible(true);
	}

	
	public void tick() {
		pet.tick();
	}

}
