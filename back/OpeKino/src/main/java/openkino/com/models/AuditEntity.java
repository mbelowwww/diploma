package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.view.Views;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class AuditEntity extends ModelEntity{

    @Column(name = "created_by")
    @JsonView(Views.Public.class)
    private String createdBy;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @Column(name = "created_when")
    @JsonView(Views.Public.class)
    private LocalDateTime createdWhen;

    @Column(name = "update_by")
    @JsonView(Views.Public.class)
    private String updatedBy;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @Column(name = "update_when")
    @JsonView(Views.Public.class)
    private LocalDateTime updatedWhen;
}
