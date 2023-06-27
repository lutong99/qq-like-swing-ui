package org.example.game.fishing;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * 继承Thread类重写run方法,并引入jar包,为游戏添加背景音乐
 *
 * @author 浩哥
 */
public class Bgm extends Thread {
    @Override
    public void run() {
        try {
            Player bgm = new Player(Objects.requireNonNull(Bgm.class.getResourceAsStream("/static/Fishing/bgm.mp3")));
            bgm.play();
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }
}
