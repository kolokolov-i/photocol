package superbro.photocol.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/file")
public class FileController {

    private String pathBase = "storage";
    private Path pathPreview = Paths.get(pathBase, "preview");
    private Path pathFull = Paths.get(pathBase, "full");

    private FileSystemResource notFound = new FileSystemResource(pathBase + "/system/notfound.jpg");

    @GetMapping("/preview/{filename}")
    @ResponseBody
    public FileSystemResource getPreview(@PathVariable("filename") String filename){
        return get(pathPreview, filename);
    }

    @GetMapping("/full/{filename}")
    @ResponseBody
    public FileSystemResource getFull(@PathVariable("filename") String filename){
        return get(pathFull, filename);
    }

    private FileSystemResource get(Path path, String name){
        Path filePath = path.resolve(name);
        if(Files.exists(filePath)){
            return new FileSystemResource(filePath);
        }
        return notFound;
    }
}
