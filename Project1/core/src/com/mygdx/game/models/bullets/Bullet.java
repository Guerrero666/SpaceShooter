package com.mygdx.game.models.bullets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.engine.Sprite;
import com.mygdx.game.engine.math.Rect;

public class Bullet extends Sprite {

    private Rect worldBounds;

    private final Vector2 v = new Vector2();

    private int damage;

    private Object owner;

    private boolean enemyBullet;


    public Bullet() {
        regions = new TextureRegion[1];
    }

    public void set(
            Object owner,
            TextureRegion region,
            Vector2 pos0,
            Vector2 v0,
            float height,
            Rect worldBounds,
            int damage,
            boolean enemyBullet
    ) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
        this.enemyBullet = enemyBullet;
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);
        if (this.pos.y>0.7f || this.pos.y<-0.7f) {
            setDestroyed(true);
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public boolean isEnemyBullet() {
        return enemyBullet;
    }

}
