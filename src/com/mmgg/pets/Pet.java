package com.mmgg.pets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.mmgg.graphics.Spritesheet;
import com.mmgg.main.Main;

public abstract class Pet extends JFrame implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	protected JLabel petLabel;
	protected Point initialClick, mouseLoc;
	protected PetMovement movement;
	protected BufferedImage[] spriteRight, spriteLeft, spriteUp, spriteDown;
	protected BufferedImage[] spriteRight_Up, spriteLeft_Up, spriteRight_Down, spriteLeft_Down;
	protected BufferedImage resizedImage;
	protected int width, height;
	protected int frames, maxFrames, index, maxIndex;
	protected double minSpd, maxSpd;
	protected boolean moved;
	
	public Pet(int x, int y, int width, int height, double minSpd, double maxSpd) {
		this.minSpd = minSpd;
		this.maxSpd = maxSpd;
		this.width = width;
		this.height = height;
		
		mouseLoc = MouseInfo.getPointerInfo().getLocation();
		
//		//Adiciona funções do mouse
		addMouseListener(this);
		addMouseMotionListener(this);
		
		//Informações da janela(frame)
		setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);
        setSize(width, height);
        setLocation(x, y);
        setLayout(null);        
        
        
        // JLabel que mostrará o sprite
        petLabel = new JLabel();
        petLabel.setBounds(0, 0, width, height);
        add(petLabel);

        setVisible(true);
        
	} 
	public void detectedMove() {
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index == maxIndex) {
				index = 0;
			}
		}	
	}
	
	public double getMinSpd() {
		return minSpd;
	}

	public double getMaxSpd() {
		return maxSpd;
	}
	
	public JLabel getPetLabel() {
        return petLabel;
    }
	
	public void setIndex(int index) {
		this.index = index;
	}

	public void loadSprites(int maxIndex, Spritesheet spritesheet, int width, int height) {
		this.maxIndex = maxIndex;
		
		spriteLeft = new BufferedImage[maxIndex];
	    spriteRight = new BufferedImage[maxIndex];
	    spriteUp = new BufferedImage[maxIndex];
	    spriteDown = new BufferedImage[maxIndex];

	    for (int i = 0; i < maxIndex; i++) {
	        spriteLeft[i] = spritesheet.getSprite(i * width, 0, width, height);
	        spriteRight[i] = spritesheet.getSprite(i * width, height, width, height);
	        spriteUp[i] = spritesheet.getSprite(i * width, height*2, width, height);
	        spriteDown[i] = spritesheet.getSprite(i * width, height*3, width, height);
	    }
	}
	public void loadSpritesD(int qntIndex, Spritesheet spritesheet, int width, int height) {
		spriteLeft_Up = new BufferedImage[qntIndex];
	    spriteRight_Up = new BufferedImage[qntIndex];
	    spriteLeft_Down = new BufferedImage[qntIndex];
	    spriteRight_Down = new BufferedImage[qntIndex];

	    for (int i = 0; i < qntIndex; i++) {
	        spriteLeft_Up[i] = spritesheet.getSprite(i * width, 0, width, height);
	        spriteRight_Up[i] = spritesheet.getSprite(i * width, height, width, height);
	        spriteLeft_Down[i] = spritesheet.getSprite(i * width, height*2, width, height);
	        spriteRight_Down[i] = spritesheet.getSprite(i * width, height*3, width, height);
	    }
	}
	public abstract void updateImage(boolean[] dir); 

	protected BufferedImage resizeImage(BufferedImage original, int width, int height) {
	    BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = resized.createGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(original, 0, 0, width, height, null);
	    g.dispose();
	    return resized;
	}
	
	protected void resizeAndSetIcon(BufferedImage sprite) {
        resizedImage = resizeImage(sprite, width, height);
        petLabel.setIcon(new ImageIcon(resizedImage));
    }

	public void tick() {
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		int rotation = e.getWheelRotation();
		System.out.println(rotation);
//		if(e.getButton() == MouseEvent.BUTTON3) {
//			 Main.trocarPet(new LizardPet(Main.defaultPet.getX(), Main.defaultPet.getY(), 100, 100, 1, 3));
//		 }else if(e.getButton() == MouseEvent.BUTTON2) {
//			 Main.trocarPet(new DefaultPet(Main.defaultPet.getX(), Main.defaultPet.getY(), 100, 100, 1, 3));
//		 }
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int thisX = getLocation().x;
        int thisY = getLocation().y;

        int xMoved = e.getX() - initialClick.x;
        int yMoved = e.getY() - initialClick.y;

        movement.setX(thisX + xMoved);
        movement.setY(thisY + yMoved);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		 initialClick = e.getPoint();
//		 if(e.getButton() == MouseEvent.BUTTON1) {
//			 System.exit(1);
//		 }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON3) {
				Main.trocarPet(new LizardPet(Main.pet.getX(), Main.pet.getY(), 300, 300, 1, 3));
		}
		if(e.getButton() == MouseEvent.BUTTON1) {
			Main.trocarPet(new DefaultPet(Main.pet.getX(), Main.pet.getY(), 300, 300, 1, 3));
		}
		if(e.getButton() == MouseEvent.BUTTON2) {
			Main.trocarPet(new StickmanPet(Main.pet.getX(), Main.pet.getY(), 300, 300, 1, 3));
		}
			
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}