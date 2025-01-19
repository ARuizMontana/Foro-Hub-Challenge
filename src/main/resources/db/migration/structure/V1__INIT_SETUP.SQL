create table roles (
    rol_id  bigint primary key auto_increment,
    name varchar(50)  unique not null
);

create table users(
    user_id bigint primary key auto_increment,
    username varchar(50)  unique not null ,
    email varchar(30)  unique not null,
    name varchar(50) not null,
    password varchar(255) not null
);

create table users_roles(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users(user_id) on delete cascade on update cascade,
    foreign key (role_id) references roles(rol_id) on delete cascade on update cascade
);

create table courses(
    course_id bigint primary key auto_increment,
    name varchar(50) not null,
    category varchar(50) unique not null
);

create table topics(
    topic_id bigint primary key auto_increment,
    title varchar(255) not null,
    message varchar(255) not null,
    status varchar(20) not null,
    created_at timestamp not null default now(),
    user_id bigint not null,
    course_id bigint not null,
    foreign key (user_id) references users(user_id),
    foreign key (course_id) references courses(course_id)
);

create table responses(
    response_id bigint primary key auto_increment,
    message varchar(255) not null,
    solution varchar(255) not null,
    created_at timestamp not null default now(),
    user_id bigint not null,
    topic_id bigint not null,
    foreign key (user_id) references users(user_id),
    foreign key (topic_id) references topics(topic_id)
);