package superbro.photocol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import superbro.photocol.entity.AppRole;
import superbro.photocol.entity.AppUser;
import superbro.photocol.repo.RoleRepo;
import superbro.photocol.repo.UserRepo;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DbUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = userRepo.getByName(userName);
        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        List<AppRole> appRoles = roleRepo.findRolesForUser(appUser.getId());
        List<GrantedAuthority> grantList = appRoles.stream().map(t -> new SimpleGrantedAuthority(t.getName())).collect(Collectors.toList());
        return new User(appUser.getName(), appUser.getPassword(), grantList);
    }

    private AppUser get(String name){
        return userRepo.getByName(name);
    }


    public AppUser from(Principal principal){
        User loggedUser = (User) ((Authentication) principal).getPrincipal();
        return get(loggedUser.getUsername());
    }
}
