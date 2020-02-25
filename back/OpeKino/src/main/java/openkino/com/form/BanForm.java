package openkino.com.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import openkino.com.exceptions.ResponseException;
import openkino.com.jpa.KinoUserDao;
import openkino.com.models.KinoUser;
import openkino.com.models.UserBan;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BanForm {

    private Long kinoUserId;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime time;
    private String description;
    private Boolean ban;//если бан то тру

    public UserBan toUserBan(KinoUser kinoUserForBan){
        UserBan userBan = new UserBan();
        userBan.setStatus(true);
        userBan.setDescription(this.description);
        userBan.setKinoUser(kinoUserForBan);
        userBan.setTime(this.time);
        userBan.setCreatedWhen(LocalDateTime.now());
        userBan.setUpdatedWhen(LocalDateTime.now());
        return userBan;
    }
}
