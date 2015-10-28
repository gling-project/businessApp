package be.gling.businessApp.model.dto;

import be.gling.businessApp.model.dto.technical.DTO;
import be.gling.businessApp.model.util.annotation.NotNull;
import be.gling.businessApp.model.util.annotation.Pattern;

import java.math.BigDecimal;

/**
 * Created by florian on 17/05/15.
 */
public class AddressDTO extends DTO {

    private Long id;

    private String name;

    private String street;

    private String zip;

    private String city;

    private String country;

    private BigDecimal posx;

    private BigDecimal posy;


    public AddressDTO() {
    }

    public AddressDTO(String street, String zip, String city, String country) {
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }

    public AddressDTO(String name, String street, String zip, String city, String country) {
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getPosx() {
        return posx;
    }

    public void setPosx(BigDecimal posx) {
        this.posx = posx;
    }

    public BigDecimal getPosy() {
        return posy;
    }

    public void setPosy(BigDecimal posy) {
        this.posy = posy;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
