package com.e59.beans;

public class QQGame {

	/** QQ 号码 */
	private String qqNumber;

	/** 游戏分数 */
	private Integer gameScore;

	/**
	 * Java bean 的空参构造器, 反射必须有
	 */
	public QQGame() {
	}

	/**
	 * 构造方法, 属性太少, 一个有参的构造器就够用了
	 * 
	 * @param qqNumber  qq号码
	 * @param gameScore qq游戏的得分
	 */
	public QQGame(String qqNumber, Integer gameScore) {
		super();
		this.qqNumber = qqNumber;
		this.gameScore = gameScore;
	}

	/**
	 * @return QQ 号码
	 */
	public String getQqNumber() {
		return qqNumber;
	}

	/**
	 * @param qqNumber 要被设置的QQ号码
	 */
	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	/**
	 * @return 游戏分数
	 */
	public Integer getGameScore() {
		return gameScore;
	}

	/**
	 * @param gameScore 需要被设置的游戏分数
	 */
	public void setGameScore(Integer gameScore) {
		this.gameScore = gameScore;
	}

	/**
	 * 同测试方法
	 */
	@Override
	public String toString() {
		return "QQGame [qqNumber=" + qqNumber + ", gameScore=" + gameScore + "]";
	}

}
