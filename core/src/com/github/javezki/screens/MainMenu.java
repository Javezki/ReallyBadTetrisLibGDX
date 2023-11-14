package com.github.javezki.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.github.javezki.game.actors.FatBird;

public class MainMenu implements Screen {

    ShapeRenderer shapeRenderer;

    Stage stage;

    @Override
    public void show() {
        shapeRenderer = new ShapeRenderer();
        stage = new Stage();
        Image backgroundImage = new Image(new Texture(Gdx.files.internal("postapocalypse1.png")));
        FatBird bird = new FatBird();
        bird.setPosition(0, Gdx.graphics.getHeight()*0.75f);
        bird.fly();
        backgroundImage.setFillParent(true);

        initButtons();

        stage.addActor(backgroundImage);
        stage.addActor(bird);
    }

    private void initButtons() {

        Skin skin = new Skin();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/MouldyCheese.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;
        BitmapFont font = generator.generateFont(parameter);

        TextButtonStyle textButtonStyle = new TextButtonStyle();

        skin.add("up-button", new Texture(Gdx.files.internal("buttons/play.png")));

        Drawable drawable = skin.getDrawable("up-button");

        textButtonStyle.up = drawable;

        textButtonStyle.font = font;
        skin.add("default", textButtonStyle);
        generator.dispose();

        TextButton playButton = new TextButton("Play", skin);
        playButton.setWidth(Gdx.graphics.getWidth() * 0.10f);
        playButton.setHeight(Gdx.graphics.getHeight() * 0.05f);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth(), Gdx.graphics.getHeight() / 2 - playButton.getHeight());
        stage.addActor(playButton);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Sets the background color to gray
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}