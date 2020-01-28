package com.stepasha.keyconservation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stepasha.keyconservation.logging.Loggable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Loggable
@Entity
@Table(name = "users")
public class User extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;
    @Column(nullable = true)
    private String profilepicture;

    @Column(nullable = false,
            unique = true)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false,
            unique = true)
    @Email
    private String primaryemail;
    private String firstname;
    private String lastname;

    private Boolean position;
    @Column(nullable = true)
    private String mini_bio;
    @Column(nullable = true)
    private String species;
    @Column(nullable = true)
    private String facebook;
    @Column(nullable = true)
    private String instagram;
    @Column(nullable = true)
    private String twitter;
    @Column(nullable = true)
    private String location;
    @Column(nullable = true)
    private String about_us;
    @Column(nullable = true)
    private String issues;


    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userroles = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Useremail> useremails = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Campaigns> campaigns = new ArrayList<>();
    public User(){}

    public User(long userid, String profilepicture, String username, String password, @Email String primaryemail, Boolean position, String firstname, String lastname, List<UserRoles> userroles, String mini_bio, String species, String facebook, String instagram, String twitter, String location, String about_us, String issues, List<Useremail> useremails, List<Campaigns> campaigns) {
        this.userid = userid;
        this.profilepicture = profilepicture;
        setUsername(username);
        setPassword(password);
        this.primaryemail = primaryemail;
        this.position = position;
        this.mini_bio = mini_bio;
        this.species = species;
        this.facebook = facebook;
        this.instagram = instagram;
        this.twitter = twitter;
        this.location = location;
        this.about_us = about_us;
        this.issues = issues;
        this.useremails = useremails;
        this.campaigns = campaigns;
        this.firstname = firstname;
        this.lastname = lastname;

        for (UserRoles ur : userroles)
        {
            ur.setUser(this);
        }
        this.userroles = userroles;
    }

    public String getMini_bio() {
        return mini_bio;
    }

    public void setMini_bio(String mini_bio) {
        this.mini_bio = mini_bio;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public Boolean getPosition() {

        return position;
    }

    public void setPosition(Boolean position) {

        this.position = position;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbout_us() {
        return about_us;
    }

    public void setAbout_us(String about_us) {
        this.about_us = about_us;
    }

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }

    public List<Campaigns> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaigns> campaigns) {
        this.campaigns = campaigns;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        if (username == null) // this is possible when updating a user
        {
            return null;
        } else
        {
            return username.toLowerCase();
        }
    }

    public void setUsername(String username)
    {
        this.username = username.toLowerCase();
    }

    public String getPrimaryemail()
    {
        if (primaryemail == null) // this is possible when updating a user
        {
            return null;
        } else
        {
            return primaryemail.toLowerCase();
        }
    }

    public void setPrimaryemail(String primaryemail)
    {
        this.primaryemail = primaryemail.toLowerCase();
    }

    public String getPassword()
    {
        return password;
    }
    //TODO 24 Encrypt the pass
    //TODO AUTH 5 ENCRYPT THE PASSWORD
    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }
    //TODO AUTH 6 NON ENCODED
    public void setPasswordNotEncrypt(String password)
    {
        this.password = password;
    }

    public List<UserRoles> getUserroles()
    {
        return userroles;
    }

    public void setUserroles(List<UserRoles> userroles)
    {
        this.userroles = userroles;
    }
    //delete those later
    public List<Useremail> getUseremails()
    {
        return useremails;
    }
    //delete those later
    public void setUseremails(List<Useremail> useremails)
    {
        this.useremails = useremails;
    }

    //TODO AUTH 4 SimpleGrantAuthority
    //TODO 23 Simple grant Auth granted to user
    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.userroles)
        {
            String myRole = "ROLE_" + r.getRole()
                    .getName()
                    .toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }


}
