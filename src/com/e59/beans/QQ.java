package com.e59.beans;

/**
 *
 * 这个就是我们的qq类了, 在这里面可以初始化成为一个对象 然后作为我们所有页面的老板对象, 传来传去
 *
 * @author  Lutong99
 *
 */
public class QQ {

	/** QQ 号码 */
	private String number;

	/** qq昵称 */
	private String nickname;

	/** QQ密码 */
	private String password;

	/** QQ头像的文件路径, 是一个相对路径, 再进行读取的时候也是一种相对路径 */
	private String photo;

	/** QQ用户的性别 */
	private String gender;

	/** 生日, 是我们的日期格式化来赋值给我们的这个字符串 */
	private String birthday;

	/** QQ用户的国家 */
	private String country;

	/** QQ用户的省份 */
	private String province;

	/** QQ用户的城市 */
	private String city;

	/** QQ用户的电子邮件 */
	private String email;

	/** QQ用户的民族 */
	private String nation;

	/** 删除状态, 1 为没有删除, 0 有删除 */
	private char state;

	/** 注册时间 */
	private String register;

	/** 等级 */
	private Integer level;

	/** 个性签名 */
	private String signature;

	/**
	 * 空参构造器, java bean所必须的, 虽然我们在这里面可能用不到反射, 但是我们要有这个使用意识
	 */
	public QQ() {

	}

	/**
	 * 所有参数的构造器
	 * 
	 * @param iD       id
	 * @param number   qq号码
	 * @param nickname 昵称
	 * @param password 密码
	 * @param photo    头像的路径
	 * @param gender   性别
	 * @param birthday 生日
	 * @param country  国家
	 * @param province 省
	 * @param city     城市
	 * @param email    电子邮件
	 * @param nation   国家
	 * @param state    状态
	 * @param register 注册时间
	 */
	public QQ(String number, String nickname, String password, String photo, String gender, String birthday,
			String country, String province, String city, String email, String nation, char state, String register,
			Integer level, String signature) {
		super();
		this.number = number;
		this.nickname = nickname;
		this.password = password;
		this.photo = photo;
		this.gender = gender;
		this.birthday = birthday;
		this.country = country;
		this.province = province;
		this.city = city;
		this.email = email;
		this.nation = nation;
		this.state = state;
		this.register = register;
		this.level = level;
		this.signature = signature;
	}

	/**
	 * 主要是在注册的时候用, {@code number} 是注册的时候进行随机分配的
	 * 
	 * @param number 我们分配的qq号码
	 * @param qq     除了没有qq号码的其他的信息的QQ
	 */
	public QQ(String number, QQ qq) {
		// nickname,password,photo, gender, birthday, country, province, city, email,
		// nation, register
		this(number, qq.getNickname(), qq.getPassword(), qq.getPhoto(), qq.getGender(), qq.getBirthday(),
				qq.getCountry(), qq.getProvince(), qq.getCity(), qq.getEmail(), qq.getNation(), '1', qq.getRegister(),
				1, qq.getSignature());

	}

	/**
	 * 获取一个没有密码, 没有存在状态的qq实例, 我们主要用来展示在我们的面板或资料上
	 * 
	 * @param number   qq号码
	 * @param nickname 昵称
	 * @param photo    头像的路径
	 * @param gender   性别
	 * @param birthday 生日
	 * @param country  国家
	 * @param province 省
	 * @param city     城市
	 * @param email    电子邮件
	 * @param nation   国家
	 * @param register 注册时间
	 * 
	 */
	public QQ(String number, String nickname, String photo, String gender, String birthday, String country,
			String province, String city, String email, String nation, String register) {
		super();
		this.number = number;
		this.nickname = nickname;
		this.photo = photo;
		this.gender = gender;
		this.birthday = birthday;
		this.country = country;
		this.province = province;
		this.city = city;
		this.email = email;
		this.nation = nation;
		this.register = register;
	}

	/**
	 * 
	 * 获取一个比较完整的qq实例, 用于修改资料
	 * 
	 * @param number   qq号码
	 * @param nickname 昵称
	 * @param password 密码
	 * @param photo    头像的路径
	 * @param gender   性别
	 * @param birthday 生日
	 * @param country  国家
	 * @param province 省
	 * @param city     城市
	 * @param email    电子邮件
	 * @param nation   国家
	 * @param state    状态
	 */
	public QQ(String number, String nickname, String password, String photo, String gender, String birthday,
			String country, String province, String city, String email, String nation, char state) {
		super();
		this.number = number;
		this.nickname = nickname;
		this.password = password;
		this.photo = photo;
		this.gender = gender;
		this.birthday = birthday;
		this.country = country;
		this.province = province;
		this.city = city;
		this.email = email;
		this.nation = nation;
		this.state = state;
	}

	/**
	 * 为了登陆的时候能用, 因为登陆的手只是使用qq号码和密码就好了
	 * 
	 * @param number   用户输入的qq号码
	 * @param password 用户输入的密码
	 */
	public QQ(String number, String password) {
		this.number = number;
		this.password = password;
	}

	/**
	 * 
	 * 用于注册的时候.
	 * 
	 * @param nickname 昵称
	 * @param password 密码
	 * @param photo    头像的路径
	 * @param gender   性别
	 * @param birthday 生日
	 * @param country  国家
	 * @param province 省
	 * @param city     城市
	 * @param email    电子邮件
	 * @param nation   国家
	 * @param state    状态
	 * @param register 注册时间
	 */
	public QQ(String nickname, String password, String photo, String gender, String birthday, String country,
			String province, String city, String email, String nation, char state, String register, int level,
			String signature) {
		this.nickname = nickname;
		this.password = password;
		this.photo = photo;
		this.gender = gender;
		this.birthday = birthday;
		this.country = country;
		this.province = province;
		this.city = city;
		this.email = email;
		this.nation = nation;
		this.state = state;
		this.register = register;
		this.level = level;
		this.signature = signature;

	}

	/**
	 * @return QQ号码
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number 需要被设置的qq号码, 但是一般情况下用不着
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return 昵称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname 要被设置的昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return 返回密码, 一般情况下也用不到
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password 需要设置的密码, 但是一般真的用不着
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return 图片路径
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photo 要被设置的头像的路径
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * @return 性别
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender 要被设置的性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return 生日
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday 需要被设置的生日值
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return 国家
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country 要设置的国家
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return 省
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province 需要被设置的省
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return 城市
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city 需要被设置的城市
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return 电子邮件
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email 要被设置的右键地址
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return 民族
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * @param nation 要被设置的民族
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}

	/**
	 * @return 状态, 真心用不到
	 */
	public char getState() {
		return state;
	}

	/**
	 * @param state 需要被设置的状态
	 */
	public void setState(char state) {
		this.state = state;
	}

	/**
	 * @return 返回注册时间
	 */
	public String getRegister() {
		return register;
	}

	/**
	 * @param register 用不到的方法, 设置注册时间
	 */
	public void setRegister(String register) {
		this.register = register;
	}

	/**
	 * @return QQ等级
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level 要被设置的QQ等级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return 个性签名
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature 需要设置的个性签名
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * 程序员测试的方法, 也就是用来打在控制台的, 没有什么实际意义
	 */
	@Override
	public String toString() {
		return "QQ [number=" + number + ", nickname=" + nickname + ", password=" + password + ", photo=" + photo
				+ ", gender=" + gender + ", birthday=" + birthday + ", country=" + country + ", province=" + province
				+ ", city=" + city + ", email=" + email + ", nation=" + nation + ", state=" + state + ", register="
				+ register + "]";
	}

}
