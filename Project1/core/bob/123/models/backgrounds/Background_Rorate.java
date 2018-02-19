package com.mygdx.game.models.backgrounds;



import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.engine.Sprite;
import com.mygdx.game.engine.math.Rect;


public class Background_Rorate extends Sprite {

    public Background_Rorate(TextureRegion region) {
        super(region);
        super.scale=1.15f;
    }


    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);

    }

    public void draw(SpriteBatch batch) {
        angle+=-0.07f;
        batch.draw(
                regions[frame], // текущий регион
                getLeft(), getBottom(), // точка отрисовки
                halfWidth, halfHeight, // точка вращения
                getWidth(), getHeight(), // ширина и высота
                scale, scale, // масштаб по x и y
                angle // угол вращения
        );
    }
}
