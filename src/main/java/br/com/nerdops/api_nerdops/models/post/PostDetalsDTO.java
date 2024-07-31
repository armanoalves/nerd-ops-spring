package br.com.nerdops.api_nerdops.models.post;

public record PostDetalsDTO(String title, String description) {
    public PostDetalsDTO(Post post) {
        this(post.getTitle(), post.getDescription());
    }
}
