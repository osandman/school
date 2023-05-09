package net.osandman.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "student")
public class Student {
    @Column(name = "user_id")
    private long userId;
    @Id
    @Column(name = "person_id")
    private long personId;
    @Column(name = "group_id")
    private long groupId;
    @Column(name = "school_id")
    private long schoolId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "sex")
    private String sex;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private StudentInfo studentInfo;
}
