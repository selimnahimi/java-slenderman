package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

public class PropTreeSmall extends Prop {

    public PropTreeSmall() {
        super();
        this.paperSurface = true;
        this.sizeX = 2;
        this.sizeY = 2;
        this.collisions = false;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/tree.png"));
    }
}
