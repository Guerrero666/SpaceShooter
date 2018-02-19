package com.mygdx.game.models.enemys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.models.actor.Ship;
import com.mygdx.game.models.bullets.BulletPool;

public class EnemyShip extends Ship {
    private TextureRegion bullet = new TextureRegion(new Texture("bullet_lite2.png"));
    private float startY = 0.6f;

    public EnemyShip() {
        regions = new TextureRegion[1];
        this.bulletRegion = bullet;
        this.bulletHeight = 0.02f;
        this.bulletV.set(0, -0.5f);
        this.bulletDamage = 1;
        this.reloadInterval = 0.2f;
        this.bulletEnemy = true;
    }

    public void set(
            TextureRegion region,
            BulletPool bulletPool,
            float startX,
            float speed
    ) {
        this.bulletPool = bulletPool;
        regions[0] = region;
        setHeightProportion(0.15f);
        pos.set(startX, startY);
        v.set(0,speed);
        this.hp = 3;
    }

    @Override
    public void update(float delta) {
        reloadTimer += delta;
        this.pos.mulAdd(v, delta);
        this.shoot();
        if (this.pos.y<-0.6f) {
            setDestroyed(true);
        }
    }


}
