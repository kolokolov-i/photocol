package superbro.photocol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import superbro.photocol.entity.AppUser;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FileService {

    private final PhotoService photoService;

    private String pathBase = "storage";
    private Path pathPreview = Paths.get(pathBase, "preview");
    private Path pathFull = Paths.get(pathBase, "full");

    private FileSystemResource notFound = new FileSystemResource(pathBase + "/system/notfound.jpg");

    private FileSystemResource get(Path path, String name){
        Path filePath = path.resolve(name);
        if(Files.exists(filePath)){
            return new FileSystemResource(filePath);
        }
        return notFound;
    }

    public FileSystemResource getPreview(String filename) {
        return get(pathPreview, filename);
    }

    public FileSystemResource getFull(String filename) {
        return get(pathFull, filename);
    }

    public void upload(AppUser user, Integer albumId, HttpServletRequest request, MultipartFile data) throws IOException {
        String originalName = data.getOriginalFilename();
        File serverFile = pathFull.resolve(originalName).toFile();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(data.getBytes());
        stream.close();
        photoService.addPhoto(user, originalName, originalName, albumId);
    }
}
