package openkino.com.jpa;

import openkino.com.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountDao extends JpaRepository<Discount, Long> {
}
