package com.llav3ji2019.application.applicationmanager.public_interface.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto {
    private Long id;
    private String username;

    @EqualsAndHashCode.Exclude
    private Set<String> roles;
}
