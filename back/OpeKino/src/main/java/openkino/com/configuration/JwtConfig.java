package openkino.com.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
public class JwtConfig {
    @Value("/auth")
    private String Uri;

    @Value("Authorization")
    private String header;

    @Value("Bearer ")
    private String prefix;

    @Value("#{7*24*60*60}")
    private int expiration;

    @Value("EXCLUSIVE_KEY")
    private String secret;
}
