package superbro.photocol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import superbro.photocol.dto.DTOAlbum;
import superbro.photocol.dto.DTOAlbumItem;
import superbro.photocol.dto.DTOUserInfo;
import superbro.photocol.entity.AppUser;
import superbro.photocol.service.AlbumsService;
import superbro.photocol.service.DbUserDetailsService;

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

    @GetMapping("/albums")
    public String albums(Model model, Principal principal){
        model.addAttribute("title", "Мои альбомы");
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        AppUser appUser = userService.get(loginedUser.getUsername());
        List<DTOAlbumItem> albums = albumService.getUserAlbums(appUser);
        model.addAttribute("albums", albums);
        model.addAttribute("info", new DTOUserInfo(albums.size(), 0));
        return "albums";
    }

    @GetMapping("/album/{albumId}")
    public String getAlbum(@PathVariable() Integer albumId, Model model, Principal principal){
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        AppUser appUser = userService.get(loginedUser.getUsername());
        DTOAlbum album = albumService.getAlbum(appUser, albumId);
        model.addAttribute("title", album.getName());
        model.addAttribute("album", album);
        return "album";
    }
}
