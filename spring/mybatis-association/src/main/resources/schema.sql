create table sex (
  id integer primary key,
  name varchar(100) not null
);

create table person (
  id integer primary key,
  name varchar(100) not null,
  sex integer not null,
  foreign key (sex) references sex(id)
);