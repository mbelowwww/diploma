package openkino.com.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import openkino.com.view.Views;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table
@Setter
@Getter
public class KinoUser extends AuditEntity implements UserDetails{

    @JsonView(Views.Public.class)
    @Column
    private String fName;

    @JsonView(Views.Public.class)
    @Column
    private String name;

    @JsonView(Views.Public.class)
    @Column
    private String lName;

    @Column
    private String password;

    @JsonView(Views.Public.class)
    @Column
    private Integer age;

    @JsonView(Views.Public.class)
    @Column
    private String phone;

    @JsonView(Views.Public.class)
    @Column(unique = true)
    private String mail;

    @JsonView(Views.Public.class)
    @Column
    private Boolean action = true;

    @ManyToOne
    @JoinColumn(unique = true, nullable = false, updatable = false)
    private Position position;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kinoUser")
    private List<Card> cards;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kinoUser")
    private List<UserBan> userBan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kinoUser")
    private List<Comment> comments;

    public KinoUser() {
    }

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    public List<UserBan> getUserBan() {
        return userBan;
    }

    @JsonIgnore
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @JsonIgnore
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("User"));
        return authorities;
    }

    @Override
    public String getUsername() {
        return getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
