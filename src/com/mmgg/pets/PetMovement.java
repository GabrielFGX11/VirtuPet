package com.mmgg.pets;

import java.util.Random;
import com.mmgg.main.Main;

public class PetMovement {

    protected static final int LEFT = 0;
    protected static final int RIGHT = 1;
    protected static final int UP = 2;
    protected static final int DOWN = 3;
    protected static final int STOP = 4;
    

    protected boolean[] dir = new boolean[5];
    private int numDirX = 0, numDirY = 0, frames;
    private double spd = 2.0, gravity = 5.0;
    private int x, y;
    private int centerX, centerY;

    private String type;
    protected boolean seguirMouse, pegouMouse;
    private Random rand;

    private final Pet pet;

    public PetMovement(Pet pet, int x, int y, String type) {
        this.pet = pet;
        this.x = x;
        this.y = y;
        this.type = type;
        this.rand = new Random();
    }

    public void tick() {
    	centerX = x+pet.width/2;
    	centerY = y+pet.height/2;
        if (type.equals("Plataform")) {
            movePlataform();
        } else if (type.equals("TopDown")) {
            moveTopDown();
        }
        updateRandomBehavior();
    }

    private void movePlataform() {
        if (y + pet.getHeight() < Main.getScreenHeight()) {
            y += gravity;
            dir[DOWN] = true;
        } else {
            if (dir[LEFT] && !dir[DOWN]) {
                x -= spd;
                if(!dir[STOP]) {
                	pet.detectedMove();
                }
            } else if (dir[RIGHT] && !dir[DOWN]) {
                x += spd;
                if(!dir[STOP]) {
                	pet.detectedMove();
                }
            }
        }

        clampToScreen();
        pet.setLocation(x, y);
    }

    private void moveTopDown() {
        if (seguirMouse) {
            seguindoMouse();
        } else {
            if (pegouMouse) {
                moveMouse();
            }

            if (dir[LEFT]) {
                x -= spd;
            } else if (dir[RIGHT]) {
                x += spd;
            }

            if (dir[UP]) {
                y -= spd;
            } else if (dir[DOWN]) {
                y += spd;
            }
        }

        clampToScreen();
        pet.setLocation(x, y);
    }

    private void clampToScreen() {
        int width = pet.getWidth();
        int height = pet.getHeight();
        int screenW = Main.getScreenWidth();
        int screenH = Main.getScreenHeight();

        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x + width > screenW) x = screenW - width;
        if (y + height > screenH) y = screenH - height;
    }

    private void updateRandomBehavior() {
        frames++;
        if (frames >= 60) {
            frames = 0;

            // Reset direções anteriores
            dir[numDirX] = false;
            dir[numDirY] = false;

            numDirX = rand.nextInt(2);          // LEFT ou RIGHT
            numDirY = rand.nextInt(2) + 3;      // UP ou DOWN

            spd = calculateSpeed();

            if (!pegouMouse) {
                seguirMouse = rand.nextBoolean();
            } else {
                seguirMouse = false;
                if(rand.nextInt(100) > 98) {
                	pegouMouse = rand.nextBoolean();
                }
            }
        }

        // Atualiza direções ativas
        dir[numDirX] = true;
        dir[numDirY] = true;

        pet.updateImage(dir);
    }

    private double calculateSpeed() {
        if (seguirMouse || rand.nextBoolean()) {
            return pet.getMaxSpd();
        } else if (rand.nextBoolean()) {
            return pet.getMinSpd();
        } else {
            return rand.nextDouble(pet.getMinSpd() + pet.getMaxSpd());
        }
    }

    private void moveMouse() {
        if (Main.robot != null) {
            Main.robot.mouseMove(centerX, centerY);
        }
    }

    private void seguindoMouse() {
        if (Math.abs(centerX - pet.mouseLoc.x) <= 1 && Math.abs(centerY - pet.mouseLoc.y) <= 1) {
            pegouMouse = true;
            return;
        }

        if (centerX < pet.mouseLoc.x) {
            x += spd;
            dir[RIGHT] = true;
        } else if (centerX > pet.mouseLoc.x) {
            x -= spd;
            dir[LEFT] = true;
        }

        if (centerY < pet.mouseLoc.y) {
            y += spd;
            dir[DOWN] = true;
        } else if (centerY > pet.mouseLoc.y) {
            y -= spd;
            dir[UP] = true;
        }
    }

    // Getters e Setters
    public int getDirX() {
        return numDirX;
    }

    public int getDirY() {
        return numDirY;
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
