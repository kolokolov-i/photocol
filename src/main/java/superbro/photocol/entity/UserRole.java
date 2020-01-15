package superbro.photocol.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue
    private Integer id;
    @JoinColumn(name = "app_user", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;
    @JoinColumn(name = "role", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AppRole role;
}
