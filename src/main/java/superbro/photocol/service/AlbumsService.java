package superbro.photocol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import superbro.photocol.dto.DTOAlbum;
import superbro.photocol.dto.DTOAlbumItem;
import superbro.photocol.entity.Album;
import superbro.photocol.entity.AppUser;
import superbro.photocol.repo.AlbumRepo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kolokolov Ivan - kolokolov.i@ext-system.com
 * @since 24.01.20
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AlbumsService {

    private final AlbumRepo repo;
    private final PhotoService photoService;

    public List<DTOAlbumItem> getUserAlbums(AppUser user) {
        return repo.findAllByUser(user).stream().map(t -> new DTOAlbumItem(t.getId(), t.getName(), t.getPathPreview())).collect(Collectors.toList());
    }

    public DTOAlbum getAlbum(AppUser user, Integer albumId){
        Album album = repo.findOneByUserAndId(user, albumId);
        DTOAlbum result = new DTOAlbum();
        result.setName(album.getName());
        result.setPhotos(photoService.getPhotosInAlbum(album));
        return result;
    }
}
