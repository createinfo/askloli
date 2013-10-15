package io.loli.askloli.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuestionType {
    public static enum QType {
        TEXTAREA("TEXTAREA"), RADIO("RADIO"), CHECKBOX("CHECKBOX"), TEXTFIELD(
                "TEXTAREA");
        String s;

        QType(String s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return s;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(nullable = false)
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}