package com.mygdx.game.models.bullets;

import com.mygdx.game.engine.pool.SpritesPool;
import com.mygdx.game.models.asteroids.Asteroid;

public class BulletPool extends SpritesPool<Bullet> {


    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

    @Override
    protected void debugLog() {
        System.out.println("BulletPool change active/free:" + activeObjects.size() + "/" + freeObjects.size());
    }
    @Override
    public void updateActiveObjects(float delta) {
        for (int i = 0; i < activeObjects.size(); i++) {
            if (!Asteroid.activeAsteroid.isEmpty()) {
                for (int j = 0; j < Asteroid.activeAsteroid.size(); j++) {
                    if (Asteroid.activeAsteroid.get(j).pos.cpy().add(0, Asteroid.activeAsteroid.get(j).getHeight()).epsilonEquals(activeObjects.get(i).pos, Asteroid.activeAsteroid.get(j).getHalfWidth())) {
                        Asteroid.activeAsteroid.get(j).setWhole(false);
                        activeObjects.get(i).setDestroyed(true);
                    }
                }
            }
            if (!activeObjects.get(i).isDestroyed()) {
                activeObjects.get(i).update(delta);
            }
        }
    }
}
