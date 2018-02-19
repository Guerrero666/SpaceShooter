package com.mygdx.game.models.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.engine.Sprite;
import com.mygdx.game.engine.math.Rect;
import com.mygdx.game.models.bullets.Bullet;
import com.mygdx.game.models.bullets.BulletPool;

public abstract class Ship extends Sprite {

    protected final Vector2 v = new Vector2(); // скорость корабля
    protected Rect worldBounds; // границы мира

    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;


    protected final Vector2 bulletV = new Vector2(); // скорость пули
    protected float bulletHeight; // высота пули
    protected  int bulletDamage; // урон

    protected float reloadInterval; // время перезарядки
    protected float reloadTimer; // таймер для стрельбы

    public Ship(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    public void shoot() {
        if (reloadTimer >= reloadInterval) {
            Bullet bullet = bulletPool.obtain();
            bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, bulletDamage);
            reloadTimer = 0f;
        }
    }
}