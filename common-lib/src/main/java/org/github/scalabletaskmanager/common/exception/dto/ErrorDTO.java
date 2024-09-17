package org.github.scalabletaskmanager.common.exception.dto;

import lombok.Data;

@Data
public class ErrorDTO {

    private String description;
    private int errorCode;
}
