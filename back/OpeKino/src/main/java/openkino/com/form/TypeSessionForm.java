package openkino.com.form;

import lombok.Data;
import openkino.com.exceptions.ResponseException;
import openkino.com.models.TypeSession;
import org.springframework.http.HttpStatus;

@Data
public class TypeSessionForm {
    private Long Id;
    private String name;

    public TypeSession toTypeSession() {
        TypeSession typeSession = new TypeSession();
        ResponseException.nullHandler(this.name, "Не заполнено поле с названием типа сеанса");
        typeSession.setName(this.name);
        return typeSession;
    }
}
