package openkino.com.jpa;

import openkino.com.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscountDao extends JpaRepository<Discount, Long> {
    @Query("select d from Discount d where d.deprecatedFrom is null")
    List<Discount> findAll();
}
