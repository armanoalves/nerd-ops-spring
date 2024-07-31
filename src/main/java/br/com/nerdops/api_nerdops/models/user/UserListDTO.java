package br.com.nerdops.api_nerdops.models.user;

public record UserListDTO(Long id, String name, String email) {
    public UserListDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
