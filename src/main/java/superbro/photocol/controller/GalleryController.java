package superbro.photocol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import superbro.photocol.dto.DTOAlbumItem;
import superbro.photocol.dto.DTOPhoto;
import superbro.photocol.dto.DTOUserInfo;
import superbro.photocol.entity.AppUser;
import superbro.photocol.service.AlbumsService;
import superbro.photocol.service.DbUserDetailsService;
import superbro.photocol.service.FileService;
import superbro.photocol.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

/**
 * @author Kolokolov Ivan - kolokolov.i@ext-system.com
 * @since 23.01.20
 */
@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GalleryController {

    private final DbUserDetailsService userService;
    private final AlbumsService albumService;
    private final PhotoService photoService;
    private final FileService fileService;

    @GetMapping("/albums")
    public String albums(Model model, Principal principal) {
        model.addAttribute("title", "Мои альбомы");
        AppUser appUser = userService.from(principal);
        List<DTOAlbumItem> albums = albumService.getUserAlbums(appUser);
        model.addAttribute("albums", albums);
        model.addAttribute("info", new DTOUserInfo(albums.size(), 0));
        return "albums";
    }


    @GetMapping("/photo/{photoId}")
    public String getPhoto(@PathVariable() Integer photoId, Model model, Principal principal) {
        AppUser appUser = userService.from(principal);
        try {
            DTOPhoto photo = photoService.getPhoto(appUser, photoId);
            model.addAttribute("photo", photo);
            return "photo";
        } catch (RuntimeException e) {
            return "redirect:";
        }
    }

    @PostMapping("/photo_add")
    public String addPhoto(@RequestParam(name = "album_id") Integer albumId,
                           MultipartFile data,
                           HttpServletRequest request,
                           Model model, Principal principal){
        AppUser appUser = userService.from(principal);
        try {
            fileService.upload(appUser, albumId, request, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/album/"+albumId;
    }
}
