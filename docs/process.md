# Summary
## 1. Fundamental Knowledge
- protected 在别的包里面子类的对象不能调用
## 2. 
- 再创建一个java bean的时候, 类型尽量不要相同, 因为在调用的时候, 构造方法只是认识个数了, 如果少输入一个参数或者两个参数的话, 调用的方法就不是你自己想要使用的方法了,
尤其是在涉及到我们的数据库的时候, 如果参数的长度不同的话, 就会出现数据库插入不进去的错误, 混淆视听, 防不胜防

## 3. 
托盘区显示中文乱码问题, AWT的问题

## 4. 

我们在进行查询语句的操作时候, 如果表中字段的名字和我们的Java Bean中属性名字不一样的话, 我们就会查询到空值, 
select cloud_name, cloud_path , cloud_time, cloud_size from qq_cloud123456 where cloud_time = ?";
如何解决, 只需要在查询的时候我们使用别名就好了, 

select cloud_name cloudName, cloud_path cloudPath, cloud_time cloudTime, cloud_size cloudSize from qq_cloud123456 where cloud_time = ?";

这是因为的queryRunner里面的查询操作使用的是反射机制, 因此我们必须要保证查询的结果列名与我们的java bean中的属性名一致

## 5.
在java swing 中我们使用的保存文件窗口时, 还需要自己写文件名, 有没有一种方法, 可以直接默认的就好了呢? , 因为Java现有的api中没有可以直接修改的这个东西, 所以我们可以用通用的方
法来获取到我们的TextField, 参数Container c 可以是任意Container 的 子类或其本身, 也包括JFileChooser

		public JTextField getTextField(Container c) {
			JTextField textField = null;
			for (int i = 0; i < c.getComponentCount(); i++) {
				Component cnt = c.getComponent(i);
				if (cnt instanceof JTextField) {
					return (JTextField) cnt;
				}
				if (cnt instanceof Container) {
					textField = getTextField((Container) cnt);
					if (textField != null) {
						return textField;
					}
				}
			}
			return textField;
		}
		
## 6.
在java swing中的jTable 判断是否为空, 只需要使用getRowsCount()方法就好了

##  7.

属性域在构造方法之前进行初始化