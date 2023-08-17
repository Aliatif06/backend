package com.project.elearning.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "enrollement")
public class Enrollement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses course;

    @Column(name = "enrollement_date")
    private LocalDate enrollementDate;

    public Enrollement(User user, Courses course, LocalDate enrollementDate) {
        this.user = user;
        this.course = course;
        this.enrollementDate = enrollementDate;
    }

    public LocalDate getEnrollementDate() {
        return enrollementDate;
    }

    public void setEnrollementDate(LocalDate enrollementDate) {
        this.enrollementDate = enrollementDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }
}
