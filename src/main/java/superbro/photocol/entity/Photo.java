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
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @JoinColumn(name = "album", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;
    private int sort;
    @Column(name = "path_preview")
    private String pathPreview;
    @Column(name = "path_full")
    private String pathFull;
//    @Type(type = "hstore")
//    private Map<String, String> attributes = new HashMap<>();
}
