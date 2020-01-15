package superbro.photocol.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import superbro.photocol.entity.AppRole;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<AppRole, Short> {

    AppRole getByName(@Param("name") String name);

    @Query(
            "select r " +
                    "from UserRole ur " +
                    "join ur.user u " +
                    "join ur.role r " +
                    "where u.id = :user "
    )
    List<AppRole> findRolesForUser(@Param("user") Integer userId);
}
