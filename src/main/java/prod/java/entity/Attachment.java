package prod.java.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import prod.java.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends AbsEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private long size;
    @Column(nullable = false)
    private String contentType;
}
