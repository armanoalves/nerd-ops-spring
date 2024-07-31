package br.com.nerdops.api_nerdops.models.user;

import br.com.nerdops.api_nerdops.models.comment.Comment;
import br.com.nerdops.api_nerdops.models.post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "User")
@Table(name = "Users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    private Boolean ativo;

    public User(UserCreateDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
        this.ativo = dto.ativo();
    }

    public void update(UserUpdateDTO dados) {
        if (dados.name() != null) {
            this.name = dados.name();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
