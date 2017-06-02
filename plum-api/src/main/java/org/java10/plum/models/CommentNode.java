package org.java10.plum.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Tate on 2017/3/30.
 */
@Getter
@Setter
public class CommentNode {

    private Integer coid;

    private Date createdAt;

    private String author;

    private String avatar;

    private String url;

    private String text;

    private String ip;

    private String agent;

    private Integer parentId = 0;

    private List<CommentNode> commentChildren = new ArrayList<>();

    //查找节点
    public CommentNode findCommentNodeById(Integer coid) {
        if (Objects.equals(this.coid, coid))
            return this;
        if (commentChildren.isEmpty() || commentChildren == null) {
            return null;
        } else {
            for (CommentNode child : commentChildren) {
                CommentNode resultNode = child.findCommentNodeById(coid);
                if (resultNode != null) {
                    return resultNode;
                }
            }
            return null;
        }
    }
}
