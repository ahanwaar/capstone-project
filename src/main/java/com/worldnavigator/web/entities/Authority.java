package com.worldnavigator.web.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorities")
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
public class Authority implements GrantedAuthority {

    @Id
    private String role;

    @ManyToMany(mappedBy = "authorities")
    private List<User> users;

    @Override
    public String getAuthority() {
        return role;
    }
}
