CREATE TABLE student
(
    user_id     bigint NOT NULL,
    person_id   bigint NOT NULL PRIMARY KEY,
    school_id   bigint,
    group_id    bigint,
    first_name  varchar(64),
    last_name   varchar(64),
    middle_name varchar(64),
    sex         varchar(8)
);

CREATE TABLE student_info
(
    person_id  bigint      NOT NULL PRIMARY KEY,
    short_name varchar(32) NOT NULL,
    birthday   date,
    phone      varchar(32),
    email      varchar(64),
    photo_url  varchar
);