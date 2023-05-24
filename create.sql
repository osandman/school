
    create table student (
       person_id int8 not null,
        first_name varchar(255),
        group_id int8,
        last_name varchar(255),
        middle_name varchar(255),
        school_id int8,
        sex varchar(255),
        user_id int8,
        primary key (person_id)
    );

    create table student_info (
       person_id int8 not null,
        birthday date,
        email varchar(255),
        phone varchar(255),
        photo_url varchar(255),
        short_name varchar(255),
        primary key (person_id)
    );

    create table Teacher
    (
        id                      bigint not null primary key,
        userId                  bigint,
        schoolId                bigint,
        firstName               varchar(255),
        lastName                varchar(255),
        middleName              varchar(255),
        sex                     varchar(255),
        dateBirth               date,
        email                   varchar(255),
        subjects                varchar(255),
        houseMaster             boolean,
        education               varchar(255),
        scientificDegree        varchar(255),
        startDate               date,
        pedagogicalActivityDate date,
        managingEmployee        boolean,
        nameManagingPosition    varchar(255),
        teachingStaff           boolean,
        nameTeacherPosition     varchar(255),
        trainingAndSupportStaff boolean,
        servicePersonnel        boolean,
        medicalWorker           boolean,
        nameMedicalPosition     varchar(255),
        externalPartTime        boolean
    );
