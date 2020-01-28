package superbro.photocol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import superbro.photocol.service.FileService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FileController {

    private final FileService fileService;

    @GetMapping("/preview/{filename}")
    @ResponseBody
    public FileSystemResource getPreview(@PathVariable("filename") String filename){
        return fileService.getPreview(filename);
    }

    @GetMapping("/full/{filename}")
    @ResponseBody
    public FileSystemResource getFull(@PathVariable("filename") String filename){
        return fileService.getFull(filename);
    }

}
