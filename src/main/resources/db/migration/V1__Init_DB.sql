create sequence hibernate_sequence start 1 increment 1;

create table author
(
    author_id      int4 not null,
    author_country varchar(255),
    author_details varchar(2048),
    author_name    varchar(255),
    filename       varchar(255),
    primary key (author_id)
);

create table book
(
    book_id            int4 not null,
    binding            varchar(255),
    circulation        int4 not null,
    description        varchar(2048) not null,
    filename           varchar(255),
    isbn               varchar(255),
    book_title         varchar(255),
    year_of_publishing varchar(255),
    price              DOUBLE PRECISION not null,
    author_id          int4,
    user_id            int4,
    primary key (book_id)
);

create table category
(
    category_id   int4 not null,
    category_name varchar(255),
    primary key (category_id)
);

create table comment
(
    id   int4 not null,
    text varchar(255),
    primary key (id)
);

create table user_role
(
    user_id int4 not null,
    roles   varchar(255)
);

create table usr
(
    id              int4    not null,
    activation_code varchar(255),
    active          boolean not null,
    email           varchar(255),
    password        varchar(255) not null,
    username        varchar(255) not null,
    primary key (id)
);

alter table if exists book
    add constraint book_author_fk
    foreign key (author_id) references author;

alter table if exists book
    add constraint book_user_fk
    foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr;