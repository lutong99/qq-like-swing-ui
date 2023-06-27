package org.example.game.fishing;

/**
 * 时间线程
 *
 * @author 浩哥
 */
public class TimeThread extends Thread {
    @Override
    /**
     * 时间线程
     */
    public void run() {
        while (true) {
            // 若isover = true 停止线程
            if (Helper.isover) {
//				this.stop();
                this.interrupt();
            }
            try {
                Thread.sleep(1000);// 每一千毫秒执行一次
                Helper.mf.time.setText(Helper.times + "");// 设置游戏界面时间
                Helper.times--;//
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
