package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.display.MainCamera;
import com.prog1.slenderman.game.display.MainView;
import com.prog1.slenderman.game.resource.Texture;

import javax.swing.*;
import java.net.URL;

public abstract class EntityVisible extends Entity {
    protected int cellX = 0;
    protected int cellY = 0;
    protected int sizeX = 1;
    protected int sizeY = 1;
    protected int layer = 0;
    protected JLabel label = new JLabel();
    protected Texture texture = Texture.fallbackTexture;

    public EntityVisible() {
        super();
    }

    public EntityVisible(int cellX, int cellY, int sizeX, int sizeY) {
        this();
        this.cellX = cellX;
        this.cellY = cellY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        System.out.println("Spawned entity at x" + cellX + " y" + cellY);

        this.alignToCameraOffset();
    }

    public void setTexture(URL textureURL) {
        this.texture.setTexture(textureURL);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public JLabel getLabel() {
        return this.label;
    }

    public void setCellX(int cellX) {
        this.setCellPos(cellX, this.cellY);
    }

    public void setCellY(int cellY) {
        this.setCellPos(this.cellX, cellY);
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return cellY;
    }

    public int getPosX() {
        return this.cellX * Game.gridSize;
    }

    public int getPosY() {
        return this.cellY * Game.gridSize;
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    public int getWidth() {
        return this.sizeX * Game.gridSize;
    }

    public int getHeight() {
        return this.sizeY * Game.gridSize;
    }

    public boolean setCellPos(int cellX, int cellY) {
        System.out.println("Trying to move from: x" + this.cellX + " y" + this.cellY + " l" + this.layer + " to x" + cellX + " y" + cellY);

        if (Game.loadedLevel.getEntity(layer, this.cellX, this.cellY) != this) return false;
        if (!Game.loadedLevel.moveEntity(layer, this.cellX, this.cellY, layer, cellX, cellY)) return false;

        this.cellX = cellX;
        this.cellY = cellY;
        alignToCameraOffset();

        return true;
    }

    public void alignToCameraOffset() {
        MainView view = Game.mainView;
        MainCamera camera = Game.mainCamera;

        //int new_x = (int) (this.pos_x * 50 * view.zoom);
        //int new_y = (int) (this.pos_y * 50 * view.zoom);
        int new_x = (int) Math.floor(this.cellX * this.getWidth() * view.zoom - camera.pos_x * view.zoom);
        int new_y = (int) Math.floor(this.cellY * this.getHeight() * view.zoom - camera.pos_y * view.zoom);

        int new_width = (int) Math.floor(this.getWidth() * view.zoom);
        int new_height = (int) Math.floor(this.getHeight() * view.zoom);

        this.label.setBounds(new_x, new_y, new_width, new_height);

        label.setIcon(this.texture.getIcon());
    }

    public void alignTexture() {
        MainView view = Game.mainView;

        int new_width = (int) Math.floor(this.getWidth() * view.zoom);
        int new_height = (int) Math.floor(this.getHeight() * view.zoom);

        this.label.setBounds(label.getX(), label.getY(), new_width, new_height);
        this.texture.resize(new_width, new_height);

        label.setIcon(this.texture.getIcon());
    }
}
