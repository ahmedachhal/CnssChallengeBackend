package com.maltem.PaymentTransactionManagement.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuditLog {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String eventType;
    private String entityType;
    private Integer entityId;
    private Date eventDate;
    private Integer userId;
    private String description;

}
