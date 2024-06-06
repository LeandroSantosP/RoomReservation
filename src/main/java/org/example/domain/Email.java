package org.example.domain;
import lombok.Getter;

@Getter
public class Email {
    private String value;

    public Email(String email){
        this.value = email;
    }

}
