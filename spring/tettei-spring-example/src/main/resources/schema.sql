create table if not exists usr (
  user_id varchar(255) primary key,
  password varchar(255) not null,
  first_name varchar(255) not null,
  last_name varchar(255) not null,
  role_name varchar(255) not null
);
