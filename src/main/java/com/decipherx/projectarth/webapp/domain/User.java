package com.decipherx.projectarth.webapp.domain;

import com.decipherx.projectarth.webapp.util.SidPrefix;
import com.decipherx.projectarth.webapp.util.UUIDGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table
public class User implements Serializable {

    private static final long serialVersionUID = -9001198124094210159L;

    @Id
    @GeneratedValue(generator = "uuid_seq")
    @GenericGenerator(
            name = "uuid_seq",
            strategy = "com.decipherx.projectarth.webapp.util.UUIDGenerator",
            parameters = {@org.hibernate.annotations.Parameter(
                    name = UUIDGenerator.VALUE_PREFIX_PARAMETER, value = SidPrefix.USER)}
    )
    @Column(name = "sid")
    private String sid;

    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Column(name = "master_email", nullable = false, unique = true)
    private String masterEmail;


//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Account> accountList = new ArrayList<>();

    public User() {
    }

    public User(@NotNull String username, @NotNull String password, @NotNull String masterEmail) {
        this.username = username;
        this.password = password;
        this.masterEmail = masterEmail;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMasterEmail() {
        return masterEmail;
    }

    public void setMasterEmail(final String masterEmail) {
        this.masterEmail = masterEmail;
    }

//    public List<Account> getAccountList() {
//        return accountList;
//    }
//
//    public void setAccountList(List<Account> accountList) {
//        this.accountList = accountList;
//    }
}
