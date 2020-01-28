package superbro.photocol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import superbro.photocol.dto.DTOPhoto;
import superbro.photocol.dto.DTOPhotoItem;
import superbro.photocol.entity.Album;
import superbro.photocol.entity.AppUser;
import superbro.photocol.entity.Photo;
import superbro.photocol.repo.AlbumRepo;
import superbro.photocol.repo.PhotoRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PhotoService {

    private final PhotoRepo photoRepo;
    private final AlbumRepo albumRepo;

    public List<DTOPhotoItem> getPhotosInAlbum(Album album) {
        return photoRepo.findAllByAlbum(album).stream().map(t -> new DTOPhotoItem(t.getId(), t.getName(), t.getPathPreview())).collect(Collectors.toList());
    }

    public DTOPhoto getPhoto(AppUser appUser, int id) {
        Photo photo = photoRepo.findOneById(id);
        Album album = photo.getAlbum();
        if (album.getUser().getId() != appUser.getId()) {
            throw new RuntimeException("security");
        }
        Photo prev = photoRepo.findOneByAlbumAndSort(album, photo.getSort() - 1);
        Photo next = photoRepo.findOneByAlbumAndSort(album, photo.getSort() + 1);
        return new DTOPhoto(photo.getId(), photo.getName(),
                photo.getPathPreview(), photo.getPathFull(),
                album.getId(), album.getName(),
                prev == null ? null : new DTOPhotoItem(prev.getId(), prev.getName(), prev.getPathPreview()),
                next == null ? null : new DTOPhotoItem(next.getId(), next.getName(), next.getPathPreview()));
    }

    public void addPhoto(AppUser user, String originalName, String pathFull, Integer albumId) {
        Album album = albumRepo.findOneByUserAndId(user, albumId);
        Photo photo = new Photo(0, originalName, null, album, 0, null, pathFull);
        photoRepo.saveAndFlush(photo);
    }
}
