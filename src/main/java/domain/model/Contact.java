package domain.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String userid, firstName, lastName, gsm, email;
    private LocalDate date;
    private LocalTime hour;

    public Contact(){ }
    public Contact(String userid, String firstname, String lastname, LocalDate date, LocalTime hour, String gsm, String email){
        setDate(date);
        setEmail(email);
        setFirstName(firstname);
        setGsm(gsm);
        setHour(hour);
        setLastName(lastname);
        setUserid(userid);
    }

    public String getUserid() {
        return userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateAsString(){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public LocalTime getHour() {
        return hour;
    }

    public String getGsm() {
        return gsm;
    }

    public String getEmail() {
        return email;
    }

    public void setUserid(String userid) {
        if(userid.isEmpty()){throw new DomainException("no userid given");}
        this.userid = userid;
    }

    public void setFirstName(String firstName) {
        if(firstName.isEmpty()){throw new DomainException("no first name given");}
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if(lastName.isEmpty()){throw new DomainException("no last name given");}
        this.lastName = lastName;
    }



    public void setDate(LocalDate date) {
        if (date == null){ throw new DomainException("No date given");}
        if ((date.isAfter(LocalDate.now()))) throw new DomainException("The date has to be before today");
        this.date = date;
    }

    public void setHour(LocalTime hour) {
        if(hour == null){
            throw new DomainException("No hour given");
        }
        this.hour = hour;
    }

    public void setGsm(String gsm) {
        if(gsm.isEmpty()){throw new DomainException("no gsm number given");}
        this.gsm = gsm;
    }

    public void setEmail(String email) {
        if(email.isEmpty()){
            throw new DomainException("No email given");
        }
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(EMAIL_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new DomainException("Email not valid");
        }
        this.email = email;
    }
}
