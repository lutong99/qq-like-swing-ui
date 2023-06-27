# TencentQQ2

#### Description

Java GUI mainly consists of AWT & Swing to imitate Tencent QQ Client. Being
familiar with OOP, three-tier architecture, and MVC(Model, View and Controller)
is my purpose.

### Designation

#### Login

- we can login with our qq number and qq password, and get the digest of qq
  password in MD5 for the security. And the data in database have the same
  process.
- For the case of forgetting password, we can use email or SMS message verify if
  the user is right. ~~ But I don't finish it ~~

#### Register

- while we register a user for this project, what we need enter is , nickname,
  password, photo, age, gender, birthday, country, province, city, nation,
  description etc.

- when the user enter the information we need, this system will generate a qq
  number, and set length of this number five.

- when we save the datas to database, we just save the digest of the password in
  md5

- meanwhile, we will create a table for our user's files. the name for this
  table is qq_cloud[qq number]. including cloud_name, cloud_path, cloud_time,
  cloud_size;

#### Cloud

- the button for the cloud will sit on our main frame, when the button is
  clicked, the cloud frame will display.

- when we open the cloud frame, we will see three button here, upload, download
  and delete.

- for upload, when we click it, we will see one file chooser, we can select what
  we wannt to upload.

- for download, when we click it, we will also see one file chooser, we can save
  the file selected by us to where we want.

- for delete, when we click it, the file will disappear in our eyes. but in our
  database, we can only set the state to 0 to stand for that the file has been
  removed.

#### QQ Move Box

- there is a qq game button in our main frame, when we click it, the move box
  game frame will display.

#### Change Info

- using the register frame for change info.

#### Find friend

- find friend with qq number

- find friend with nickname

- find friend with gender

- find friend with ages

#### Show friends list

- using scroll bar

- some tabs

## Database designation

### Register

- user table

  | Column name     | Column type               | Extra           |
    | ----------- | ---------------------- | -------------- |
  | qq_id       | int primary key        | qq id |
  | qq_number   | int(5) not null unique | qq number         |
  | qq_nickname | varchar(12)            | nickname          |
  | qq_password | char(32)               | digest of password      |
  | qq_photo    | blob                   | profile           |
  | qq_gender   | varchar(4)             | gender          |
  | qq_birthday | date                   | birthday           |
  | qq_country  | varchar(20)            | country           |
  | qq_province | varchar(10)            | province             |
  | qq_city     | varchar(10)            | city           |
  | qq_nation   | varchar(10)            | nation           |
  | qq_email     | varchar(25)            | email           |
  | qq_state    | char(1) default '1'    | state, 0 is delete, 1 is exist         |
  | qq_register | datetime               | register time       |

### Cloud

qq_cloud[user name]
| column name | column type | Extra |
| ----------- | ------------------- | --------------------------- |
| cloud_id | int primary key | id of file we save |
| cloud_address | varchar(255)        | path of our file |
| cloud_state | char(1) default '1' | state, 0 is delete, 1 is exist |
| cloud_time | datetime | time we save the file |




















