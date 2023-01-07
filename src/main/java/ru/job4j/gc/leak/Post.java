package ru.job4j.gc.leak;

import java.util.List;
import java.util.Objects;

public class Post {

    private final String text;
    private final List<Comment> comments;
    private int id;

    public Post(String text, List<Comment> comments) {
        this.text = text;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(text, post.text) && Objects.equals(comments, post.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, comments);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id="
                + id + ", text='"
                + text + '\''
                + ", comments="
                + comments
                + '}';
    }
}