package com.hust.exam.models.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedNativeQuery(name = "StudentScore",
        query = "SELECT u.username as username, u.first_name as firstName, u.last_name as lastName, t.score as score FROM tests t join system_users u on t.student_id = u.username WHERE t.exam_id = :examId",
        resultSetMapping = "StudentResult")
@SqlResultSetMapping(
        name="StudentResult",
        classes={
                @ConstructorResult(
                        targetClass=com.hust.exam.models.statistic.StudentScore.class,
                        columns={
                                @ColumnResult(name="username", type=String.class),
                                @ColumnResult(name="firstName", type=String.class),
                                @ColumnResult(name="lastName", type=String.class),
                                @ColumnResult(name="score", type=Float.class)
                        })})
@Entity
public class StudentScore {
    @Id
    private String username;
    private String firstName;
    private String lastName;
    private Float score;

}
