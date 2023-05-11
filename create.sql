
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

    create table teacher (
       id int8 not null,
        dateBirth date,
        education varchar(255),
        email varchar(255),
        externalPartTime boolean not null,
        firstName varchar(255),
        houseMaster boolean not null,
        lastName varchar(255),
        managingEmployee boolean not null,
        medicalWorker boolean not null,
        middleName varchar(255),
        nameManagingPosition varchar(255),
        nameMedicalPosition varchar(255),
        nameTeacherPosition varchar(255),
        pedagogicalActivityDate date,
        scientificDegree varchar(255),
        servicePersonnel boolean not null,
        sex varchar(255),
        startDate date,
        subjects varchar(255),
        teachingStaff boolean not null,
        trainingAndSupportStaff boolean not null,
        userId int8 not null,
        primary key (id)
    );
