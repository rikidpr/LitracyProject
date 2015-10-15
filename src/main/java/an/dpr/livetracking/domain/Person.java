package an.dpr.livetracking.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import an.dpr.livetracking.bean.Gender;

@Entity
@Table
public class Person {
    
    private Long id;
    private String document;
    private String name;
    private String surname;
    private String club;//TODO entity
    private Date birthDate;
    private Gender gender;
    


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @Column
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Column
    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    @Column
    public String getClub() {
	return club;
    }

    public void setClub(String club) {
	this.club = club;
    }

    @Column
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
	return "Person [id=" + id + ", document=" + document + ", name=" + name + ", surname=" + surname + ", club="
		+ club + ", birthDate=" + birthDate + ", gender=" + gender + "]";
    }
}
