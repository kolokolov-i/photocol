package superbro.photocol.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_role")
public class AppRole {

    @Id
    @GeneratedValue
    private short id;
    @Basic(optional = false)
    private String name;
}
