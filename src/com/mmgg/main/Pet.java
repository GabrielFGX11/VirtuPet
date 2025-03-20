package com.mmgg.main;

import java.awt.Color;
import java.awt.Graphics2D;
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

public class Pet extends JFrame implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	
	private JLabel petLabel;
	private int width, height;
	private double minSpd, maxSpd;
	private PetMovement movement;
	private Point initialClick;
	private BufferedImage[] sprite, sprite1;
	private BufferedImage resizedImage;
//	protected static Spritesheet spritesLizard;

	
	public Pet(int x, int y, int width, int height, double minSpd, double maxSpd) {
		this.minSpd = minSpd;
		this.maxSpd = maxSpd;
		this.width = width;
		this.height = height;
//		spritesLizard = new Spritesheet("res/spritesLizard.png");
		
		movement = new PetMovement("");
		
		sprite = new BufferedImage[4];
		sprite1 = new BufferedImage[4];
		
		for(int i = 0; i <= 3; i++) {
			sprite[i] = Main.spritesLizard.getSprite(0+(i*200), 0, 200, 200);
			sprite1[i] = Main.spritesLizard.getSprite(0+(i*200), 200, 200, 200);
		}
		//Adiciona funções do mouse
		addMouseListener(this);
		addMouseMotionListener(this);
		
		//Informações da janela(frame)
		setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);
        setLayout(null);        
        
        resizedImage = resizeImage(sprite[0], width, height);
        
        //Transformando a imagem em Icon para criar um rótulo(Label)
        ImageIcon petIcon = new ImageIcon(resizedImage);
        petLabel = new JLabel(petIcon);
        petLabel.setBounds(0, 0, resizedImage.getWidth(), resizedImage.getHeight());
        add(petLabel);
        setSize(resizedImage.getWidth(), resizedImage.getHeight());
        
	} 
	
	public double getMinSpd() {
		return minSpd;
	}

	public double getMaxSpd() {
		return maxSpd;
	}

	public void updateImage(boolean[] dir) {
		if(dir[0] && dir[3]) {
			resizedImage = resizeImage(sprite1[0], width, height);
			petLabel.setIcon(new ImageIcon(resizedImage));
		}else if(dir[1] && dir[3]) {
			resizedImage = resizeImage(sprite1[1], width, height);
			petLabel.setIcon(new ImageIcon(resizedImage));
		}
		else 
		if(dir[0] && dir[2]) {
			resizedImage = resizeImage(sprite1[2], width, height);
			petLabel.setIcon(new ImageIcon(resizedImage));
		}else if(dir[1] && dir[2]) {
			resizedImage = resizeImage(sprite1[3], width, height);
			petLabel.setIcon(new ImageIcon(resizedImage));
		}
		else
		if(dir[0]) {
			resizedImage = resizeImage(sprite[0], width, height);
			petLabel.setIcon(new ImageIcon(resizedImage));
		}else if(dir[1]) {
			resizedImage = resizeImage(sprite[1], width, height);
			petLabel.setIcon(new ImageIcon(resizedImage));
		}else
		if(dir[2]) {
			resizedImage = resizeImage(sprite[2], width, height);
			petLabel.setIcon(new ImageIcon(resizedImage));
		}else if(dir[3]) {
			resizedImage = resizeImage(sprite[3], width, height);
			petLabel.setIcon(new ImageIcon(resizedImage));
		}
	}
	
	private BufferedImage resizeImage(BufferedImage original, int width, int height) {
	    BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = resized.createGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(original, 0, 0, width, height, null);
	    g.dispose();
	    return resized;
	}
	
	public void tick() {
		movement.walkAndStop();
        movement.randomDir();
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
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
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
