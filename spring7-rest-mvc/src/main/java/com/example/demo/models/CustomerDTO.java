package com.example.demo.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class CustomerDTO {
    private UUID id;
    private String customerName;
    private Integer version;
    private String customerEmail;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
