package com.mygdx.game.models.enemys;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.engine.math.Rect;
import com.mygdx.game.engine.pool.SpritesPool;
import com.mygdx.game.models.bullets.Bullet;
import com.mygdx.game.models.bullets.BulletPool;

import java.util.List;

public class EnemyShipPool extends SpritesPool<EnemyShip> {

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip();
    }
    public  List<EnemyShip> getActiveObjects() {
        return activeObjects;
    }
}