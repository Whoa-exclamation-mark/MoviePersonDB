package com.zenoton.MoviePersonDB.movie;

import javax.persistence.*;

@Entity
@Table(name = "rating", catalog = "MoviePersonDB",schema = "dbo")
public class Rating {
    public Rating(){super();}
    //TODO add enum
    public Rating(String abbreviatedName, boolean adult, String fullName){
        this.abbreviatedName = abbreviatedName;
        this.adult = adult;
        this.fullName = fullName;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //E.g. R, MA, M, PG, G, E
    private String abbreviatedName;

    private boolean adult;

    private String fullName;

    public Long getId() {
        return id;
    }

    public String getAbbreviatedName() {
        return abbreviatedName;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getFullName() {
        return fullName;
    }

    public void setAbbreviatedName(String abbreviatedName) {
        this.abbreviatedName = abbreviatedName;

    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        if (adult != rating.adult) return false;
        if (!id.equals(rating.id)) return false;
        if (abbreviatedName != null ? !abbreviatedName.equals(rating.abbreviatedName) : rating.abbreviatedName != null)
            return false;
        return fullName != null ? fullName.equals(rating.fullName) : rating.fullName == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (abbreviatedName != null ? abbreviatedName.hashCode() : 0);
        result = 31 * result + (adult ? 1 : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", abbreviatedName='" + abbreviatedName + '\'' +
                ", adult=" + adult +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
