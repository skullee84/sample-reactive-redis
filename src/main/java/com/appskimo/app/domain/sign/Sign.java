package com.appskimo.app.domain.sign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Sign implements Serializable {

    private Long signId;
    private String account;
    private String remoteAddress;
    private LocalDateTime createdDateTime;

}
