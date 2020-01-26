package superbro.photocol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOPhotoItem {
    private int id;
    private String name;
    private String pathPreview;
}
