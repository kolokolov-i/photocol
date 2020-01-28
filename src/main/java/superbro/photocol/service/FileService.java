package superbro.photocol.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import superbro.photocol.entity.Album;
import superbro.photocol.entity.Photo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author Kolokolov Ivan - kolokolov.i@ext-system.com
 * @since 28.01.20
 */
@Service
public class FileService {

    private String pathBase = "storage";
    private Path pathPreview = Paths.get(pathBase, "preview");
    private Path pathFull = Paths.get(pathBase, "full");

    private FileSystemResource notFound = new FileSystemResource(pathBase + "/system/notfound.jpg");
    private FileSystemResource noImage = new FileSystemResource(pathBase + "/system/noimage.jpg");

    private FileSystemResource get(Path path, String name){
        Path filePath = path.resolve(name);
        if(Files.exists(filePath)){
            return new FileSystemResource(filePath);
        }
        return notFound;
    }

    public FileSystemResource noImage(){
        return noImage;
    }

    public FileSystemResource getPreview(String filename) {
        return get(pathPreview, filename);
    }

    public FileSystemResource getFull(String filename) {
        return get(pathFull, filename);
    }

    public Photo upload(MultipartFile file, Album album) throws IOException {
        String randomName = UUID.randomUUID().toString();
        String originalName = file.getOriginalFilename();
        File storageFile = new File(pathFull.toString(), randomName);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(storageFile));
        stream.write(file.getBytes());
        stream.close();
        return new Photo(0, originalName, null, album, 0, null, randomName);
    }
}
