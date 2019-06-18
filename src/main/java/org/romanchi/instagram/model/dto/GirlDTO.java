package org.romanchi.instagram.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GirlDTO {
    private String url;
    private String credentials;
    private Integer age;
    private String photoUrl;
}
