package org.java10.plum.models.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Tate on 2017/2/27.
 */
@Entity
@Table(name = "tags")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Tag extends BaseModel {

    @Column(nullable = false, unique = true, length = 50)
    private String name;

}
