package br.com.nerdops.api_nerdops.repositories;

import br.com.nerdops.api_nerdops.models.post.PostSearchDTO;
import br.com.nerdops.api_nerdops.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByAtivoTrue(Pageable pageable);

    User findUserByNameContainingIgnoreCase(String name);

UserDetails findByEmail(String username);
}
