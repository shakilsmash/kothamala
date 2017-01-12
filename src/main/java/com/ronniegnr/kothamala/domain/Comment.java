package com.ronniegnr.kothamala.domain;

import com.ronniegnr.kothamala.domain.enumeration.CommentStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "comment")
public class Comment extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1;

    private long id;
    private CommentStatus status;
    private long parentCommentId;
    private long postId;
    private long userId;
    private String commentText;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    @Size(max = 10)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    public CommentStatus getStatus() {
        return status;
    }

    public void setStatus(CommentStatus status) {
        this.status = status;
    }

    @Column(name = "parent_comment_id")
    public long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    @Column(name = "post_id")
    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @NotNull
    @Size(min = 1)
    @Column(name = "comment_text", nullable = false, length = 5000)
    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
