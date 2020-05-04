package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @Column(name = "created_by")
    private String createdBy;
    @JsonIgnore
    @Column(name = "created_when")
    private LocalDateTime createdWhen;
    @JsonIgnore
    @Column(name = "update_by")
    private String updatedBy;
    @JsonIgnore
    @Column(name = "update_when")
    private LocalDateTime updatedWhen;
}
