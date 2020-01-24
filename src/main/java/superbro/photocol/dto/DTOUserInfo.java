package superbro.photocol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kolokolov Ivan - kolokolov.i@ext-system.com
 * @since 24.01.20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOUserInfo {

    private int albumsCount;
    private int photosCount;
}
