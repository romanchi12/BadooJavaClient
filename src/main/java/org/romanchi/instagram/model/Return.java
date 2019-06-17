package org.romanchi.instagram.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Return<T> {
    private Integer code;
    private String message;
    private T object;
}
