package prod.java.entity;


import lombok.*;
import prod.java.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Attachment extends AbsEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private long size;
    @Column(nullable = false)
    private String contentType;
}
