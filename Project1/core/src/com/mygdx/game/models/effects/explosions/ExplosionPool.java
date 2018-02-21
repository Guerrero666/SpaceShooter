package com.mygdx.game.models.effects.explosions;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.engine.pool.SpritesPool;

public class ExplosionPool extends SpritesPool<Explosion> {

    private Sound sound;
    private TextureAtlas explosionAtlas;

    public ExplosionPool(TextureAtlas atlas, Sound sound) {
        explosionAtlas = atlas;
        this.sound = sound;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(explosionAtlas, sound);
    }
}

