package com.ronniegnr.kothamala.domain;

import com.ronniegnr.kothamala.domain.enumeration.PostStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "post")
public class Post extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1;

    private long id;
    private PostStatus status;
    private int like_count;
    private int dislike_count;
    private String created_by;
    private Timestamp created_date;
    private String last_modified_by;
    private Timestamp last_modified_date;
    private int comment_count;
    private long user_id;
    private String title;
    private String post_text;

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
    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    @Column(name = "like_count")
    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    @NotNull
    @Column(name = "dislike_count")
    public int getDislike_count() {
        return dislike_count;
    }

    public void setDislike_count(int dislike_count) {
        this.dislike_count = dislike_count;
    }

    @Column(name = "comment_count")
    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    @NotNull
    @Column(name = "user_id", nullable = false)
    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @NotNull
    @Column(name = "title", nullable = false, length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    @Column(name = "post_Text", nullable = false, length = 100)
    public String getPost_text() {
        return post_text;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

}
