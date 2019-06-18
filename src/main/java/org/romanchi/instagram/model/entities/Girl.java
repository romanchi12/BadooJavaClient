package org.romanchi.instagram.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Girls")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Girl {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "credentials")
    private String credentials;
    @Column(name = "age")
    private Integer age;
    @Column(name = "photoUrl")
    private String photoUrl;
}
