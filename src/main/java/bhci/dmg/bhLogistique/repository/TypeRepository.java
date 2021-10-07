package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByCodeType(String CodeType);  
    Type findByIdType(Long idType);    
}
