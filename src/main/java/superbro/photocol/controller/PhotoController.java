package superbro.photocol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import superbro.photocol.dto.DTOPhoto;
import superbro.photocol.entity.AppUser;
import superbro.photocol.service.DbUserDetailsService;
import superbro.photocol.service.PhotoService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

/**
 * @author Kolokolov Ivan - kolokolov.i@ext-system.com
 * @since 23.01.20
 */
@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PhotoController {

    private final DbUserDetailsService userService;
    private final PhotoService photoService;

    @GetMapping("/photo/{photoId}")
    public String getPhoto(@PathVariable() Integer photoId, Model model, Principal principal) {
        AppUser appUser = userService.from(principal);
        try {
            DTOPhoto photo = photoService.getPhoto(appUser, photoId);
            model.addAttribute("title", photo.getName());
            model.addAttribute("photo", photo);
            return "photo";
        } catch (RuntimeException e) {
            return "redirect:";
        }
    }

    @PostMapping("/photo_add")
    public String addPhoto(Principal principal,
                           @RequestParam(name = "album_id") Integer albumId,
                           @RequestParam(name = "photo_files") List<MultipartFile> files){
        try {
            photoService.addPhotos(userService.from(principal), albumId, files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/album/"+albumId;
    }

    @PostMapping("/photo_delete")
    public String deletePhoto(Principal principal,
                              @RequestParam(name = "photo_id") Integer photoId,
                              @RequestParam(name = "album_id") Integer albumId){
        photoService.deletePhoto(userService.from(principal), albumId, photoId);
        return "redirect:/album/"+albumId;
    }
}
