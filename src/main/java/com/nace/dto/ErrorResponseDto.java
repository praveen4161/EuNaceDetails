package com.nace.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents api error response.
 *
 *
 */
@Data
@AllArgsConstructor
public class ErrorResponseDto implements Serializable {

    private static final long serialVersionUID = -2347326451715687191L;

    private String message;
    private List<String> details;
    private LocalDateTime timestamp;
}
