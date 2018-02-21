package com.mygdx.game.models.bullets;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.engine.pool.SpritesPool;
import com.mygdx.game.models.actor.Hero;
import com.mygdx.game.models.asteroids.Asteroid;
import com.mygdx.game.models.effects.explosions.ExplosionMini;
import com.mygdx.game.models.effects.explosions.ExplosionMiniPool;
import com.mygdx.game.models.enemys.EnemyShipPool;

public class BulletPool extends SpritesPool<Bullet> {
    Sound shootSound;

    public BulletPool(Sound shootSound) {
        this.shootSound = shootSound;
    }

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

    @Override
    protected void debugLog() {
        System.out.println("BulletPool change active/free:" + activeObjects.size() + "/" + freeObjects.size());
    }

    public void updateActiveObjects(float delta, EnemyShipPool shipPool, Hero hero, ExplosionMiniPool explosionMiniPool) {
        for (int i = 0; i < activeObjects.size(); i++) {
            if (!Asteroid.activeAsteroid.isEmpty()) {
                for (int j = 0; j < Asteroid.activeAsteroid.size(); j++) {
                    if (Asteroid.activeAsteroid.get(j).pos.cpy().add(0, Asteroid.activeAsteroid.get(j).getHeight()).epsilonEquals(activeObjects.get(i).pos, Asteroid.activeAsteroid.get(j).getHalfWidth()) && Asteroid.activeAsteroid.get(j).getWhole()) {
                        Asteroid.activeAsteroid.get(j).setWhole(false);
                        activeObjects.get(i).setDestroyed(true);
                    }
                }
            }
            if (!shipPool.getActiveObjects().isEmpty()) {
                for (int k = 0; k < shipPool.getActiveObjects().size(); k++) {
                    if (!activeObjects.get(i).isEnemyBullet()) {
                        if (shipPool.getActiveObjects().get(k).pos.cpy().add(0, 0).epsilonEquals(activeObjects.get(i).pos, shipPool.getActiveObjects().get(k).getHalfWidth())) {
                            shootSound.play();
                            ExplosionMini explosion = explosionMiniPool.obtain();
                            explosion.set(activeObjects.get(i).pos);
                            int damage = shipPool.getActiveObjects().get(k).getHp() - 1;
                            shipPool.getActiveObjects().get(k).setHp(damage);
                            activeObjects.get(i).setDestroyed(true);
                        }
                    }
                    if (activeObjects.get(i).isEnemyBullet()) {
                        if (hero.pos.cpy().add(0, 0).epsilonEquals(activeObjects.get(i).pos, hero.getHalfHeight())) {
                            shootSound.play();
                            ExplosionMini explosion = explosionMiniPool.obtain();
                            explosion.set(activeObjects.get(i).pos);
                            hero.setHp(hero.getHp()- shipPool.getActiveObjects().get(k).damage);
                            activeObjects.get(i).setDestroyed(true);
                        }
                    }
                }
            }
            if (!activeObjects.get(i).isDestroyed()) {
                activeObjects.get(i).update(delta);
            }
        }
    }
}
