package prod.java.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import prod.java.entity.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
    AttachmentContent findByAttachmentId(UUID id);
}
