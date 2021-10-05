package com.pangaea.consumer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Subscriber.class, mappedBy = "topic")
    Set<Subscriber> subscriberList;
}
