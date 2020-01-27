package superbro.photocol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import superbro.photocol.dto.DTOPhoto;
import superbro.photocol.dto.DTOPhotoItem;
import superbro.photocol.entity.Album;
import superbro.photocol.entity.AppUser;
import superbro.photocol.entity.Photo;
import superbro.photocol.repo.PhotoRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PhotoService {

    private final PhotoRepo repo;

    public List<DTOPhotoItem> getPhotosInAlbum(Album album) {
        return repo.findAllByAlbum(album).stream().map(t -> new DTOPhotoItem(t.getId(), t.getName(), t.getPathPreview())).collect(Collectors.toList());
    }

    public DTOPhoto getPhoto(AppUser appUser, int id) {
        Photo photo = repo.findOneById(id);
        Album album = photo.getAlbum();
        if (album.getUser().getId() != appUser.getId()) {
            throw new RuntimeException("security");
        }
        Photo prev = repo.findOneByAlbumAndSort(album, photo.getSort() - 1);
        Photo next = repo.findOneByAlbumAndSort(album, photo.getSort() + 1);
        return new DTOPhoto(photo.getId(), photo.getName(),
                photo.getPathPreview(), photo.getPathFull(),
                album.getId(), album.getName(),
                prev == null ? null : new DTOPhotoItem(prev.getId(), prev.getName(), prev.getPathPreview()),
                next == null ? null : new DTOPhotoItem(next.getId(), next.getName(), next.getPathPreview()));
    }
}
