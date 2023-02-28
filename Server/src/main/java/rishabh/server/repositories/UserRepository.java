package rishabh.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rishabh.server.models.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
    @Query("select u from User u where u.email =:email")
    User getByEmail(@Param("email") String email);

    @Query("select u from User u where u.id =:id")
    User getById(@Param("id") Long id);
}
