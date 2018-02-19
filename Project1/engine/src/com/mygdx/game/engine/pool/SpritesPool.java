package com.mygdx.game.engine.pool;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.engine.Sprite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class SpritesPool<T extends Sprite> {

    // список активных объектов
    protected final List<T> activeObjects = new LinkedList<T>();

    // список свободных объектов
    protected final List<T> freeObjects = new ArrayList<T>();

    protected final List<Sprite> anyObjects = new ArrayList<Sprite>();

    protected abstract T newObject();

    public T obtain() {
        T object;
        if (freeObjects.isEmpty()) {
            object = newObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        activeObjects.add(object);
        debugLog();
        return object;
    }

    public void updateActiveObjects(float delta) {
        for (int i = 0; i < activeObjects.size(); i++) {
            activeObjects.get(i).update(delta);
        }
    }

    public void drawActiveObjects(SpriteBatch batch) {
        for (int i = 0; i < activeObjects.size(); i++) {
            activeObjects.get(i).draw(batch);
        }
    }

    public void freeAllDestroyedActiveObjects() {
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            if (sprite.isDestroyed()) {
                free(sprite);
                i--;
                sprite.setDestroyed(false);
            }
        }
    }

    public void free(T object) {
        if (!activeObjects.remove(object)) {
            throw new RuntimeException("Попытка удаления несуществующего объекта");
        }
        freeObjects.add(object);
    }

    protected  void debugLog() {

    }
    public void dispose() {
        activeObjects.clear();
        freeObjects.clear();
    }
}
