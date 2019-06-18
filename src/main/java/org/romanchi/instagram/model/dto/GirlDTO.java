package org.romanchi.instagram.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.romanchi.instagram.model.entities.Girl;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GirlDTO {
    private String url;
    private String credentials;
    private Integer age;
    private String photoUrl;

    public Girl toEntity(){
        return Girl.builder()
                .age(age)
                .credentials(credentials)
                .photoUrl(photoUrl)
                .build();
    }
}
