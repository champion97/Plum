package org.java10.plum.models.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Tate on 2017/2/27.
 */
@Entity
@Table(name = "articles")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Article extends BaseModel {

    @Column(name = "slug", length = 200)
    private String slug;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "content", nullable = false)
    @Type(type="text")
    private String content;

    @Column(name = "cover_url")
    private String cover;

    @Column(name = "comment_num", nullable = false)
    private Integer commentNum;

    @Column(name = "allow_comment")
    private Boolean allowComment;

    @Column(name = "status", nullable = false)
    private Integer status = 1;

    @Column(name = "created_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    @JoinColumn(nullable = false, name = "cid")
    private Category category;

    @ManyToMany
    @JoinTable(name = "article_tag_relations",
            joinColumns = {@JoinColumn(name = "article_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", nullable = false, updatable = false)}
    )
    private Set<Tag> tags = new HashSet<>();

}
