package rishabh.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rishabh.server.models.Vault;

public interface VaultRepository extends JpaRepository<Vault,String> {
    
     @Query("select v from Vault v where v.key= :key ")
     Vault findByKey(@Param("key") String key);
}
