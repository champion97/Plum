package org.java10.plum.models.domain;

import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Tate on 2017/2/27.
 */
@MappedSuperclass
@ToString
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
