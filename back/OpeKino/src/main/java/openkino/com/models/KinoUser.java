package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
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
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class KinoUser implements UserDetails {

    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @JsonView(Views.Public.class)
    @Column
    private String fName;

    @JsonView(Views.Public.class)
    @Column
    private String name;

    @JsonView(Views.Public.class)
    @Column
    private String lName;

    @JsonView(Views.Public.class)
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
    private List<Comment> comments;

    public KinoUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Card> getCards() {
        return cards;
    }

    @JsonIgnore
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @JsonIgnore
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
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

    public String getLName() {
        return lName;
    }

    public void setLName(String lname) {
        this.lName = lname;
    }
}
