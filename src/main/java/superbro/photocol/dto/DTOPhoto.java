package superbro.photocol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kolokolov Ivan - kolokolov.i@ext-system.com
 * @since 27.01.20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOPhoto {

    private int id;
    private String name;
    private String pathPreview;
    private String pathFull;
    private DTOPhotoItem prev;
    private DTOPhotoItem next;
}
