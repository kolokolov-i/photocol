package superbro.photocol.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Kolokolov Ivan - kolokolov.i@ext-system.com
 * @since 24.01.20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
//@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class Album {

    @Id
    @GeneratedValue
    private int id;
    @Basic(optional = false)
    private String name;
    private String description;
    @JoinColumn(name = "owner", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;
    @Column(name = "path_preview")
    private String pathPreview;
//    @Type(type = "hstore")
//    private Map<String, String> attributes = new HashMap<>();
}
