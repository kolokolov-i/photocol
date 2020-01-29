package superbro.photocol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import superbro.photocol.dto.DTOAlbum;
import superbro.photocol.dto.DTOAlbumItem;
import superbro.photocol.dto.DTOUserInfo;
import superbro.photocol.entity.AppUser;
import superbro.photocol.service.AlbumsService;
import superbro.photocol.service.DbUserDetailsService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AlbumController {

    private final DbUserDetailsService userService;
    private final AlbumsService albumService;

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
//        model.addAttribute("selectPreview", album.);
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
                            @RequestParam(name = "preview", defaultValue = "-1") Integer preview,
                            @RequestParam(name = "description", required = false) String albumDescription) {
        AppUser appUser = userService.from(principal);
        albumService.editAlbum(appUser, albumId, albumName, preview, albumDescription);
        return "redirect:/album/" + albumId;
    }

    @PostMapping("/album_delete")
    public String deleteAlbum(Principal principal,
                              @RequestParam(name = "album_id") Integer albumId) {
        AppUser appUser = userService.from(principal);
        albumService.deleteAlbum(appUser, albumId);
        return "redirect:/albums";
    }
}
