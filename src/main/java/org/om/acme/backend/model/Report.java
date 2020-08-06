package org.om.acme.backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "reports")
public class Report implements Serializable {
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
    private Long idReport;
    private LocalDate date;
    private Time hour;
    @ManyToOne()
    @JoinColumn(name = "idUser")
    private User user;

    public Report() {}

    public Report(Long idReport, LocalDate date, Time hour, User user) {
        this.idReport = idReport;
        this.date = date;
        this.hour = hour;
        this.user = user;
    }

    public Long getIdReport() {
        return idReport;
    }

    public void setIdReport(Long idReport) {
        this.idReport = idReport;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Report{" + date + '}';
    }
}