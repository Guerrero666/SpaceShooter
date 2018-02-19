package com.mygdx.game.models.backgrounds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.engine.Sprite;
import com.mygdx.game.engine.math.Rect;

public class Background_Game extends Sprite{
    float y;
    public Background_Game(TextureRegion region, float y) {
        super(region);
        this.y=y;
    }

    @Override
    public void resize(Rect worldBounds) {
        setWithProportion(worldBounds.getWidth());
        pos.set(worldBounds.pos);
    }

    public void draw(SpriteBatch batch) {
        this.y-=0.0002f;
        batch.draw(
                regions[frame], // текущий регион
                getLeft(), this.y, // точка отрисовки
                halfWidth, halfHeight, // точка вращения
                getWidth(), getHeight(), // ширина и высота
                scale, scale, // масштаб по x и y
                angle // угол вращения
        );
    }
}
