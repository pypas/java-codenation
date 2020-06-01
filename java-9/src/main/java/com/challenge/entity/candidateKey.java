package com.challenge.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class candidateKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private company company;

    @ManyToOne
    @JoinColumn(name = "acceleration_id")
    private acceleration acceleration;
}
