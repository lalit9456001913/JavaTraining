package com.example.sbfeb7.resources.users.entities;

import com.example.sbfeb7.resources.companies.entities.Company;
import com.example.sbfeb7.resources.token.entities.Token;
import com.example.sbfeb7.resources.token.entities.TokenType;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column
    private UUID id;

    @Column
    @NotBlank
    private String mobileNumber;

    @Column
    private int countryCode;

    @Column
    private LocalDateTime mobileVerifiedAt;

    @Column()
    @CreatedDate
    private Instant createdAt;

    @Column()
    @LastModifiedDate
    private Instant updatedAt;

    @Column
    private String name;

    @Column
    private String emailId;

    @Column
    private UUID recruiterManagerId;

    @Builder.Default
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "company_users",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "company_id", referencedColumnName = "id") }
    )
    private Set<Company> companies = new HashSet<>();

    public void addCompany(Company company) {
        this.companies.add(company);
    }

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "user_role_type")
    @Type(PostgreSQLEnumType.class)
    public UserRoleType role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return "IGNORED";
    }

    @Override
    public String getUsername() {
        return mobileNumber;
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
