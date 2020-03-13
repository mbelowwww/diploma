package openkino.com.service;

import openkino.com.models.AuditEntity;

import java.time.LocalDateTime;

public class Helper {
    public static void auditOnCreate(Object o){
        AuditEntity entity = (AuditEntity) o;
        entity.setCreatedWhen(LocalDateTime.now());
        entity.setUpdatedWhen(LocalDateTime.now());
    }
    public static void auditOnUpdate(Object o){
        AuditEntity entity = (AuditEntity) o;
        entity.setUpdatedWhen(LocalDateTime.now());
    }
}
