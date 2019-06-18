package org.romanchi.instagram.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Girls")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Girl {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "credentials")
    private String credentials;
    @Column(name = "age")
    private Integer age;
    @Column(name = "photoUrl")
    private String photoUrl;
}
