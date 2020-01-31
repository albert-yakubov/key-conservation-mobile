package com.stepasha.keyconservation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stepasha.keyconservation.logging.Loggable;

import javax.persistence.*;
import java.util.Date;

@Loggable
@Entity
@Table(name = "campaigns")
public class Campaigns extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @Column(nullable = true)
    private String banner_image;
    @Column(nullable = true)
    private String location;
    @Column(nullable = true)
    private Float latitude;
    @Column(nullable = true)
    private Float longitude;
    @Column(nullable = true)
    private Date created_at;
    @Column(nullable = true)
    private String event_image;
    @Column(nullable = true)
    private String event_name;
    @Column(nullable = true)
    private String event_description;

    @ManyToOne
    @JoinColumn(name = "users",
            nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    public Campaigns(){}

    public Campaigns(long id, String title, String banner_image, String location, Float latitude, Float longitude, Date created_at, String event_image, String event_name, String event_description, User user) {
        this.id = id;
        this.title = title;
        this.banner_image = banner_image;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.created_at = created_at;
        this.event_image = event_image;
        this.event_name = event_name;
        this.event_description = event_description;
        this.user = user;
    }

    public long getEventid() {
        return id;
    }

    public void setEventid(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getEvent_image() {
        return event_image;
    }

    public void setEvent_image(String event_image) {
        this.event_image = event_image;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
