package openkino.com.jpa;

import openkino.com.models.UserBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BanDao extends JpaRepository<UserBan, Long> {
    List<UserBan> findAllByKinoUser_Id(Long kinoUserId);

    @Query(value = "SELECT ub.*\n" +
            "FROM PUBLIC.USERBAN ub\n" +
            "WHERE ub.TIME <= SYSDATE\n" +
            "  AND ub.STATUS is true ",nativeQuery = true)
    List<UserBan> findAllByStatusAndTimeAfter();

    List<UserBan> findAllByStatusTrue();
}
