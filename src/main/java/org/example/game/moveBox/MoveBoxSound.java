package org.example.game.moveBox;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import java.io.File;
import java.util.Objects;

/**
 * 推向子的声音面板
 *
 * @author 来源自网络
 */
public class MoveBoxSound {
    String path = "/static/music/";// 目录
    String file = "flourish.mid";// 默认的文件名
    boolean sign;// 是否播放音乐的标志
    Sequence seq;// 序列
    Sequencer midi;// 类型变量 流播放器

    public MoveBoxSound() {
        loadSound();// 播放音乐

    }

    void loadSound() {// 播放音乐
        try {
            seq = MidiSystem.getSequence(Objects.requireNonNull(MoveBoxSound.class.getResource(path + file)));
            midi = MidiSystem.getSequencer();
            midi.open();
            midi.setSequence(seq);
            midi.start();
            midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();

        }
        sign = true;
    }

    public void mystop() {// 关闭音乐
        if (isplay()) {
            midi.stop();// 停止播放
            midi.close();// 关闭设备
            sign = false;
        }

    }

    public boolean isplay() {// 判断sign的值
        return sign;

    }

    void setmusic(String e) {// 获取音乐文件 一来默认音乐文件
        file = e;
    }
}
