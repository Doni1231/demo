package prod.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prod.java.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {

}
