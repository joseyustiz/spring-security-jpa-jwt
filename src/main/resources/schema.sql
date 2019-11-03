drop table if exists phone;
create table phone (
    id binary not null,
    number varchar(255) not null,
    city_code varchar(255) not null,
    country_code varchar(255),
    primary key (id)
);

drop table if exists user;
create table user (
    id binary not null,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    created timestamp not null,
    last_login timestamp not null,
    modified timestamp not null,
    token varchar(255) not null,
    active boolean not null,
    roles varchar(255) not null,
    primary key (id)
);

drop table if exists user_phones;
create table user_phones (
    user_id binary not null,
    phones_id binary not null
);

alter table user add constraint UK_user_email unique (email);
alter table user_phones add constraint UK_user_phones_phones_id unique (phones_id);
alter table user_phones add constraint FK_user_phones_phones_id foreign key (phones_id) references phone;
alter table user_phones add constraint FK_user_phones_user_id foreign key (user_id) references user;
