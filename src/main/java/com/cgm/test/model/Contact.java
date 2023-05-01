package com.cgm.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "contact")
@Table(name = "contact")
@Builder
public class Contact {
    @Id
    @Column(name = "matrix_id")
    private String matrixId;
    @Column(name ="name")
    private String name;
    @Column(name ="tenant_id")
    private String tenantId;
}
