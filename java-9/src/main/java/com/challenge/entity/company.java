package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class company {
    @OneToMany(mappedBy = "company")
    private List<candidate> candidates;

    @NotNull
    @Size(min=1, max=100)
    private String name;

    @NotNull
    @Size(min=1, max=50)
    private String slug;

    @NotNull
    @CreatedDate
    private LocalTime createdAt;
}
