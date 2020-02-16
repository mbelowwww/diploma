package openkino.com.jpa;

import openkino.com.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageDao extends JpaRepository<Image, Long> {
    @Query(value = "SELECT Image.image_array FROM Image  WHERE Image.id = :id", nativeQuery = true)
    byte[] findImageById(@Param("id") Long id);
}
