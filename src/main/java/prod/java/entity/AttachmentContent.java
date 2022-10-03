package prod.java.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import prod.java.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AttachmentContent extends AbsEntity {
    @OneToOne(fetch = FetchType.LAZY)
    private Attachment attachment;

    @Column(nullable = false)
    private byte[] content;
}
