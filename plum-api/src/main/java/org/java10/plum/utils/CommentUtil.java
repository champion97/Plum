package org.java10.plum.utils;

import org.java10.plum.models.CommentNode;
import org.java10.plum.models.domain.Comment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Tate on 2017/5/3.
 */
public class CommentUtil {
    //构建评论多叉树列表结构
    public static List<CommentNode> buildCommentsTree(List<Comment> comments, boolean isDesc) {
        List<CommentNode> commentTreeList = new ArrayList<>();
        List<CommentNode> nodeList = commentsToCommentNodeList(comments);
        //升序排序
        sortComments(nodeList);
        for (CommentNode commentNode : nodeList) {
            if (commentNode.getParentId() == 0) {
                commentTreeList.add(commentNode);
            } else {
                insertToCommentTree(commentTreeList, commentNode);
            }
        }
        //是否降序
        if (isDesc) {
            sortCommentsDesc(commentTreeList);
        }
        return commentTreeList;
    }

    //向多叉树列表中插入节点
    private static void insertToCommentTree(List<CommentNode> commentTreeList, CommentNode children) {
        for (CommentNode commentNode : commentTreeList) {
            CommentNode parentNode = commentNode.findCommentNodeById(children.getParentId());
            if (parentNode != null) {
                parentNode.getCommentChildren().add(children);
                break;
            }
        }
    }

    //评论升序排序
    private static void sortComments(List<CommentNode> commentNodes) {
        commentNodes.sort(Comparator.comparing(CommentNode::getCoid));
    }

    //评论降序排序
    private static void sortCommentsDesc(List<CommentNode> commentNodes) {
        commentNodes.sort(Comparator.comparing(CommentNode::getCoid).reversed());
    }

    //评论List转换
    private static List<CommentNode> commentsToCommentNodeList(List<Comment> comments) {
        List<CommentNode> nodeList = new ArrayList<>();
        for (Comment comment : comments) {
            CommentNode commentNode = new CommentNode();
            commentNode.setCoid(comment.getId());
            commentNode.setCreatedAt(comment.getCreatedAt());
            commentNode.setAuthor(comment.getAuthor());
            String mailMD5 = EncodeUtil.string2MD5(comment.getMail());
            String avatar = "https://secure.gravatar.com/avatar/" + mailMD5 + "?s=32&r=G&d=mm";
            commentNode.setAvatar(avatar);
            commentNode.setUrl(comment.getUrl());
            commentNode.setIp(comment.getIp());
            commentNode.setAgent(comment.getAgent());
            commentNode.setText(comment.getText());
            commentNode.setParentId(comment.getParentId());
            nodeList.add(commentNode);
        }
        return nodeList;
    }
}
