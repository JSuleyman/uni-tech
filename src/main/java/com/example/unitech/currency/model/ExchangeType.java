package com.example.unitech.currency.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.List;
@XmlRootElement(name = "ValType")
@XmlType(propOrder = { "valType", "valute" })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class ExchangeType {
    private  String valType;
    private List<ExchangeRate> valute;


    @XmlAttribute(name = "Type")
    public String getValType() {
        return valType;
    }

    public void setValType(String valType) {
        this.valType = valType;
    }
    @XmlElement(name = "Valute")
    public List<ExchangeRate> getValute() {
        return valute;
    }

    public void setValute(List<ExchangeRate> valute) {
        this.valute = valute;
    }


}
