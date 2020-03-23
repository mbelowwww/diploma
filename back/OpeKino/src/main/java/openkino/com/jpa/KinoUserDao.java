package openkino.com.jpa;

import openkino.com.models.KinoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KinoUserDao extends JpaRepository<KinoUser, Long> {
    @Query("SELECT k FROM KinoUser k WHERE k.mail = ?1")
    KinoUser findByMale(String mail);

    @Query("SELECT k FROM KinoUser k " +
            "WHERE " +
            "(UPPER(k.fName) LIKE CONCAT('%',UPPER(?1),'%') OR k.fName IS NULL) AND " +
            "(UPPER(k.name) LIKE CONCAT('%',UPPER(?2),'%') OR k.name IS NULL) AND " +
            "(UPPER(k.lName) LIKE CONCAT('%',UPPER(?3),'%') OR k.lName IS NULL) AND" +
            "(UPPER(k.mail) LIKE CONCAT('%',UPPER(?4),'%') OR k.mail IS NULL)")
    List<KinoUser> findByName(String fname, String name, String lname, String mail);
}
