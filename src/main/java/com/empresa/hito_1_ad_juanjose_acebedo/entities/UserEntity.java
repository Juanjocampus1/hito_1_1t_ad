package com.empresa.hito_1_ad_juanjose_acebedo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Getter
@Setter
public class UserEntity implements Serializable {
    private String name;
    private String email;
    private String password;
}