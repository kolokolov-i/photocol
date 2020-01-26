package superbro.photocol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import superbro.photocol.dto.DTOPhotoItem;
import superbro.photocol.entity.Album;
import superbro.photocol.repo.PhotoRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PhotoService {

    private final PhotoRepo repo;

    public List<DTOPhotoItem> getPhotosInAlbum(Album album){
        return repo.findAllByAlbum(album).stream().map(t -> new DTOPhotoItem(t.getId(), "", t.getPathPreview())).collect(Collectors.toList());
    }
}
