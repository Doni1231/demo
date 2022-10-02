package prod.java.entity.template;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public abstract class AbsNameEntity extends AbsEntity {
    @Column(nullable = false, unique = true)
    private String name;

    private boolean active;

    @Column(columnDefinition = "text")
    private String description;
}
