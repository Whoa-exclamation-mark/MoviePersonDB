package com.zenoton.MoviePersonDB.security;

import com.zenoton.MoviePersonDB.movie.Rating;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

@Entity
@Table(name = "users", catalog = "MoviePersonDB",schema = "dbo")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Users
    @Column(nullable = false, unique = true)
    private String username;

    //Users password (stored in salted SHA256)
    private String password;

    //Users salt for the password
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private int salt;

    //The privilege a user has (read, read/write, admin...)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_privileges",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Set<Privilege> privileges;

    //The rating that a user can see
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_rating",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "rating_id", referencedColumnName = "id"))
    private Set<Rating> rating;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getSalt() {
        return salt;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public Set<Rating> getRating() {
        return rating;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public void setRating(Set<Rating> rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (salt != user.salt) return false;
        if (!id.equals(user.id)) return false;
        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if (privileges != null ? !privileges.equals(user.privileges) : user.privileges != null) return false;
        return rating != null ? rating.equals(user.rating) : user.rating == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + salt;
        result = 31 * result + (privileges != null ? privileges.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt=" + salt +
                ", privileges=" + privileges +
                ", rating=" + rating +
                '}';
    }
}
