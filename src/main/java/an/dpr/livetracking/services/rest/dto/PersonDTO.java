package an.dpr.livetracking.services.rest.dto;

import java.util.Date;

import an.dpr.livetracking.bean.Gender;
import an.dpr.livetracking.domain.Person;

public class PersonDTO {
    
    public Long id;
    public String document;
    public String name;
    public String surname;
    public String club;
    public Long birthDate;
    public Gender gender;
    
    public PersonDTO(){}
    
    public PersonDTO(Person person){
	id = person.getId();
	document = person.getDocument();
	name= person.getName();
	surname = person.getSurname();
	club = person.getClub();
	birthDate = person.getBirthDate() != null ? person.getBirthDate().getTime() : null;
	gender = person.getGender();
    }
    
    public Person createPerson(){
	Person person = new Person();
	person.setId(id);
	person.setDocument(document);
	person.setName(name);
	person.setSurname(surname);
	person.setClub(club);
	person.setBirthDate(new Date(birthDate));
	person.setGender(gender);
	
	return person;
    }
    
    

}
