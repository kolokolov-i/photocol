package superbro.photocol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import superbro.photocol.dto.DTOPhoto;
import superbro.photocol.dto.DTOPhotoItem;
import superbro.photocol.entity.Album;
import superbro.photocol.entity.AppUser;
import superbro.photocol.entity.Photo;
import superbro.photocol.repo.AlbumRepo;
import superbro.photocol.repo.PhotoRepo;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PhotoService {

    private final PhotoRepo photoRepo;
    private final AlbumRepo albumRepo;
    private final FileService fileService;

    public List<DTOPhotoItem> getPhotosInAlbum(Album album) {
        return photoRepo.findAllByAlbum(album).stream().map(t -> new DTOPhotoItem(t.getId(), t.getName(), t.getPathPreview())).collect(Collectors.toList());
    }

    public DTOPhoto getPhoto(AppUser appUser, int id) {
        Photo photo = photoRepo.findOneById(id);
        Album album = photo.getAlbum();
        if (album.getUser().getId() != appUser.getId()) {
            throw new RuntimeException("security");
        }
        Photo prev = photoRepo.findPrev(album.getId(), photo.getSort());
        Photo next = photoRepo.findNext(album.getId(), photo.getSort());
        return new DTOPhoto(photo.getId(), photo.getName(),
                photo.getPathPreview(), photo.getPathFull(),
                album.getId(), album.getName(),
                prev == null ? null : new DTOPhotoItem(prev.getId(), prev.getName(), prev.getPathPreview()),
                next == null ? null : new DTOPhotoItem(next.getId(), next.getName(), next.getPathPreview()));
    }

    public void addPhotos(AppUser user, Integer albumId, List<MultipartFile> files) throws IOException {
        Album album = albumRepo.findOneById(albumId);
        if (album == null) {
            throw new RuntimeException("security");
        }
        for (MultipartFile file: files) {
            Photo photo = fileService.upload(file, album);
//            photo.setPathPreview();
            photoRepo.saveAndFlush(photo);
        }
    }

    public void deletePhoto(AppUser user, Integer albumId, Integer photoId) {
        Photo photo = photoRepo.findOneById(photoId);
        if (photo.getAlbum().getUser().getId() != user.getId()){
            throw new RuntimeException("security");
        }
        photoRepo.delete(photo);
    }

    @Transactional
    public void deleteFromAlbum(Album album) {
        photoRepo.deleteAllFromAlbum(album);
    }
}
