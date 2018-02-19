package com.mygdx.game.control;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.engine.ActionListener;
import com.mygdx.game.engine.math.Rect;
import com.mygdx.game.engine.ui.ScaledTouchUpButton;

public class PlayButton extends ScaledTouchUpButton {


    public PlayButton(TextureRegion region, float pressScale, ActionListener actionListener) {
        super(region, pressScale, actionListener);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setLeft(worldBounds.getLeft());
    }
}
