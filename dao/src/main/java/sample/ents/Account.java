package sample.ents;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * User: jules
 * Date: 4/20/14
 */
@NamedQueries({
        @NamedQuery(name = Account.FIND_BY_ID, query = "select a from Account a where a.id=:id"),
        @NamedQuery(name = Account.FIND_BY_NAME, query = "select a from Account a where a.name like :name"),
        @NamedQuery(name = Account.FIND_BY_DOB, query = "select a from Account a where a.dateOfBirth = :dob"),
})
@Entity
@Table
public class Account {

    public static final String FIND_BY_ID = "findById";
    public static final String FIND_BY_NAME = "findByName";
    public static final String FIND_BY_DOB = "findByDob";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    @Column
    private String name;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
    @Column
    private String email;

    /**
     * Required by JPA.
     */
    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "[Account number = '" + id + "', name = " + name + "'] ";
    }
}
