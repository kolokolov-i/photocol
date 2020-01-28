package superbro.photocol.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import superbro.photocol.entity.Album;
import superbro.photocol.entity.Photo;

import java.util.List;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Integer> {

    List<Photo> findAllByAlbum(@Param("album") Album album);

    Photo findOneById(@Param("id") Integer id);

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM photo " +
                    "WHERE album = :album " +
                    "AND sort < :sort " +
                    "ORDER BY sort DESC " +
                    "LIMIT 1"
    )
    Photo findPrev(@Param("album") Integer album, @Param("sort") Integer sort);

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM photo " +
                    "WHERE album = :album " +
                    "AND sort > :sort " +
                    "ORDER BY sort ASC " +
                    "LIMIT 1"
    )
    Photo findNext(@Param("album") Integer album, @Param("sort") Integer sort);
}
