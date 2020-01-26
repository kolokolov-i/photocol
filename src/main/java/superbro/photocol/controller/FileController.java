package superbro.photocol.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller("/file")
public class FileController {

    private String pathBase = "files";
    private Path pathPreview = Paths.get(pathBase, "preview");
    private Path pathFull = Paths.get(pathBase, "full");

    private FileSystemResource notFound = new FileSystemResource("files/system/notfound.jpg");

    @GetMapping("/preview/{filename}")
    @ResponseBody
    public FileSystemResource getPreview(@PathVariable("filename") String filename){
        Path filePath = pathPreview.resolve(filename);
        if(Files.exists(filePath)){
            return new FileSystemResource(filePath);
        }
        return notFound;
    }

    @GetMapping("/full/{filename}")
    @ResponseBody
    public FileSystemResource getFull(@PathVariable("filename") String filename){
        Path filePath = pathFull.resolve(filename);
        if(Files.exists(filePath)){
            return new FileSystemResource(filePath);
        }
        return notFound;
    }
}
