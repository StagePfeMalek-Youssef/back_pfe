package com.example.demo.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "Id_Message")
    private Long IdM;
    @Column(nullable = false, name = "Nom_User")
    private String nomUser;
    @Column(nullable = false, name = "Message")
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "Date_Déclaration")
    private Date DateDeclar = new Date(System.currentTimeMillis());
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_Sujet", nullable = false)
    private Sujet sujet;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_User")
    private User user;

    public Messages() {
    }

    public Messages(String nomUser, String message, Date dateDeclar, Sujet sujet, User user) {
        this.nomUser = nomUser;
        this.message = message;
        DateDeclar = dateDeclar;
        this.sujet = sujet;
        this.user = user;
    }

    public Long getIdM() {
        return IdM;
    }

    public void setIdM(Long idM) {
        IdM = idM;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateDeclar() {
        return DateDeclar;
    }

    public void setDateDeclar(Date dateDeclar) {
        DateDeclar = dateDeclar;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Messages [DateDeclar=" + DateDeclar + ", IdM=" + IdM + ", message=" + message + ", sujet=" + sujet
                + "]";
    }

}
