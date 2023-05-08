CREATE TABLE student
(
    student_id serial      NOT NULL PRIMARY KEY,
    id         bigint      NOT NULL,
    personId   bigint      NOT NULL,
    firstName  varchar(64),
    lastName   varchar(64),
    middleName varchar(64),
    shortName  varchar(64) NOT NULL,
    sex        varchar(8),
    birthday   date,
    role       varchar,
    groupId    bigint,
    schoolId   bigint
)