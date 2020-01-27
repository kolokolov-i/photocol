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
import superbro.photocol.entity.AppUser;
import superbro.photocol.service.AlbumsService;
import superbro.photocol.service.DbUserDetailsService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AlbumController {

    private final DbUserDetailsService userService;
    private final AlbumsService albumService;

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
}
