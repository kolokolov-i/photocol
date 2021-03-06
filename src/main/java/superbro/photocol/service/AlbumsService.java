package superbro.photocol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import superbro.photocol.dto.DTOAlbum;
import superbro.photocol.dto.DTOAlbumItem;
import superbro.photocol.dto.DTOPhoto;
import superbro.photocol.dto.DTOPhotoItem;
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
        Album album = repo.findOneById(albumId);
        if (album.getUser().getId() != user.getId()) {
            throw new RuntimeException("security");
        }
        DTOAlbum result = new DTOAlbum();
        result.setId(album.getId());
        result.setName(album.getName());
        result.setDescription(album.getDescription());
        result.setPhotos(photoService.getPhotosInAlbum(album));
        return result;
    }

    public void newAlbum(AppUser user, String albumName, String albumDescription) {
        Album album = new Album(0, albumName, albumDescription, user, null);
        repo.saveAndFlush(album);
    }

    public void editAlbum(AppUser user, Integer albumId, String albumName, int preview, String description) {
        Album album = repo.findOneById(albumId);
        if (album.getUser().getId() != user.getId()) {
            throw new RuntimeException("security");
        }
        if(albumName != null && !albumName.isEmpty()){
            album.setName(albumName);
        }
        album.setDescription(description);
        if(preview != -1){
            DTOPhoto previewPhoto = photoService.getPhoto(user, preview);
            album.setPathPreview(previewPhoto.getPathPreview());
        }
        repo.saveAndFlush(album);
    }

    public void deleteAlbum(AppUser user, Integer albumId) {
        Album album = repo.findOneById(albumId);
        if (album.getUser().getId() != user.getId()) {
            throw new RuntimeException("security");
        }
        photoService.deleteFromAlbum(album);
        repo.delete(album);
    }
}
