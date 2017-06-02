package org.java10.plum.models.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Tate on 2017/2/27.
 */
@Entity
@Table(name = "comments")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseModel {

    @Column(name = "aid", nullable = false)
    private Integer aid;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "author", nullable = false, length = 100)
    @NotEmpty
    @Size(min = 2,max = 100)
    private String author;

    @Column(name = "mail", length = 100)
    @NotNull
    @Email
    @Size(min = 2,max = 100)
    private String mail;

    @Column(name = "url", length = 100)
    @URL
    @Size(min = 2,max = 100)
    private String url;

    @Column(name = "ip", length = 64)
    private String ip;

    @Column(name = "agent", length = 200)
    private String agent;

    @Column(name = "text", nullable = false)
    @NotNull
    @Size(min = 2,max = 400)
    private String text;

    @Column(name = "status", nullable = false)
    private boolean audited;

    @Column(name = "created_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;

}
