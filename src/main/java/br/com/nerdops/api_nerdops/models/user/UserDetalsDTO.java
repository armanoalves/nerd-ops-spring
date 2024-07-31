package br.com.nerdops.api_nerdops.models.user;

public record UserDetalsDTO(String name, String email) {
    public UserDetalsDTO(User user) {
        this(user.getName(), user.getEmail());
    }
}
