package superbro.photocol.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import superbro.photocol.entity.Album;
import superbro.photocol.entity.AppUser;

import java.util.List;

/**
 * @author Kolokolov Ivan - kolokolov.i@ext-system.com
 * @since 24.01.20
 */
@Repository
public interface AlbumRepo extends JpaRepository<Album, Integer> {

    List<Album> findAllByUser(@Param("user") AppUser user);

    Album findOneById(@Param("id") Integer id);
}
