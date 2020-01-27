package superbro.photocol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import superbro.photocol.dto.DTOAlbum;
import superbro.photocol.dto.DTOAlbumItem;
import superbro.photocol.dto.DTOPhoto;
import superbro.photocol.dto.DTOUserInfo;
import superbro.photocol.entity.AppUser;
import superbro.photocol.service.AlbumsService;
import superbro.photocol.service.DbUserDetailsService;
import superbro.photocol.service.PhotoService;

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

    @GetMapping("/albums")
    public String albums(Model model, Principal principal) {
        model.addAttribute("title", "Мои альбомы");
        AppUser appUser = userService.from(principal);
        List<DTOAlbumItem> albums = albumService.getUserAlbums(appUser);
        model.addAttribute("albums", albums);
        model.addAttribute("info", new DTOUserInfo(albums.size(), 0));
        return "albums";
    }

    @GetMapping("/album/{albumId}")
    public String getAlbum(@PathVariable() Integer albumId, Model model, Principal principal) {
        AppUser appUser = userService.from(principal);
        DTOAlbum album = albumService.getAlbum(appUser, albumId);
        model.addAttribute("title", album.getName());
        model.addAttribute("album", album);
        return "album";
    }

    @PostMapping("/album_new")
    public String newAlbum(Principal principal,
                           @RequestParam(name = "album_name") String albumName,
                           @RequestParam("description") String albumDescription) {
        AppUser appUser = userService.from(principal);
        albumService.newAlbum(appUser, albumName, albumDescription);
        return "redirect:/albums";
    }

    @PostMapping("/album_edit")
    public String editAlbum(Principal principal,
                            @RequestParam(name = "album_id") Integer albumId,
                            @RequestParam(name = "album_name") String albumName,
                            @RequestParam(name = "description", required = false) String albumDescription) {
        AppUser appUser = userService.from(principal);
        albumService.editAlbum(appUser, albumId, albumName, albumDescription);
        return "redirect:/album/" + albumId;
    }

    @PostMapping("/album_delete")
    public String deleteAlbum(Principal principal,
                              @RequestParam(name = "album_id") Integer albumId) {
        AppUser appUser = userService.from(principal);
        albumService.deleteAlbum(appUser, albumId);
        return "redirect:/albums";
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
}
