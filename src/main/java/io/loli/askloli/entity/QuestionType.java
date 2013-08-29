package io.loli.askloli.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuestionType {
    enum QType {
        TEXTAREA("TEXTAREA"), RADIO("REDIO"), CHECKBOX("CHECKBOX"), TEXTFIELD(
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
    private QType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QType getType() {
        return type;
    }

    public void setType(QType type) {
        this.type = type;
    }
}