package com.github.javezki.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FatBird extends Actor {

    private Animation<TextureRegion> animation;

    private float elapsedTime = 0;

    private final int SCALE_SIZE = 6;

    private final float FRAME_DURATION = 0.1f;

    private boolean isFlipped = false;

    private int animationState = 0;

    public FatBird() {
        fly();
    }

    @Override
    public void act(float delta) {

        // Super important to call the act method of the parent class

        super.act(delta);

        if (animationState == 0) {
            float changeY = (float) Math.sin(elapsedTime * 6) * 5;
            float changeX = (float) Math.sin(elapsedTime) * Gdx.graphics.getWidth() * 0.0030f;

            if (changeX < 0)
                isFlipped = true;
            else
                isFlipped = false;

            moveBy(changeX, changeY);
        }

        // Accumulate elapsed time

        elapsedTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        // Super important to call the draw method of the parent class

        super.draw(batch, parentAlpha);

        // Get the current frame based on the elapsed time since looping is true, no
        // need to
        // reset the elapsed time
        TextureRegion currentFrame = animation.getKeyFrame(elapsedTime, true);

        if (isFlipped && !currentFrame.isFlipX())
            currentFrame.flip(true, false);
        else if (!isFlipped && currentFrame.isFlipX())
            currentFrame.flip(true, false);
        // Get the width and height of the current frame and scale it

        float width = currentFrame.getRegionWidth() * SCALE_SIZE; // double the width
        float height = currentFrame.getRegionHeight() * SCALE_SIZE; // double the height

        // Draw the current frame

        batch.draw(currentFrame, getX(), getY(), width, height);
    }

    public void fly() {

        // Loads the sprite sheet from the assets folder
        Texture sheet = new Texture(Gdx.files.internal("sprites/fatbird/Fat_bird_fly_spritesheet.png"));

        // Create the animation with the frames and frame duration

        animation = new Animation<TextureRegion>(FRAME_DURATION, cutFrames(sheet));
    }

    //Bird hit animation

    public void hit() {

        Texture sheet = new Texture(Gdx.files.internal("sprites/fatbird/Fat_bird_hit_spritesheet.png"));

        animation = new Animation<>(FRAME_DURATION, cutFrames(sheet));

        animationState = 1;
    }

    private TextureRegion[] cutFrames(Texture sheet) {
        // Gets the width and height of each frame

        int frameWidth = sheet.getWidth() / 5;
        int frameHeight = sheet.getHeight();

        // Splits the texture into frames based on the width and height

        TextureRegion[][] tmp = TextureRegion.split(sheet, frameWidth, frameHeight);

        // Since there is only 1 row of the sprite sheet, we only need to
        // get the first row for a frame

        TextureRegion[] frames = new TextureRegion[5];
        int index = 0;
        for (int j = 0; j < tmp[0].length; j++) {
            frames[index++] = tmp[0][j];
        }

        return frames;
    }

}
