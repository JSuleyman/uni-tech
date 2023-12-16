package com.example.unitech.currency;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@XmlRootElement(name = "ValCurs")
@XmlType(propOrder = { "date", "name", "description","valTypes" })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class ExchangeCours {

    private String date;
    private String name;
    private String description;
    private List<ExchangeType> valTypes;

    @XmlAttribute(name = "Date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    @XmlAttribute(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name ="Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "ValType")
    public List<ExchangeType> getValTypes() {
        return valTypes;
    }

    public void setValTypes(List<ExchangeType> valTypes) {
        this.valTypes = valTypes;
    }


}
