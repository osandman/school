package net.osandman.school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    private long id;
    private String id_str;
    private String name;
    private String knowledgeArea;
    private int fgosSubjectId;
    private String espSubjectName;


    public String getName(long id) {
        return this.id == id ? this.name : "unknown subject";
    }
}
