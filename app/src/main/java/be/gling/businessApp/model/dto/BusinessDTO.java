package be.gling.businessApp.model.dto;

import be.gling.businessApp.model.contant.BusinessStatusEnum;
import be.gling.businessApp.model.contant.ValidationRegex;
import be.gling.businessApp.model.dto.technical.DTO;
import be.gling.businessApp.model.util.annotation.NotNull;
import be.gling.businessApp.model.util.annotation.Pattern;
import be.gling.businessApp.model.util.annotation.Size;

import java.util.Date;

/**
 */
public class BusinessDTO extends DTO {

    private Long id;

    private String name;

    private String description;

    private String phone;

    private String email;

    private String website;

    private String vta;

    private AddressDTO address;

    private StoredFileDTO illustration;

    private StoredFileDTO landscape;

    private Integer totalFollowers;

    private BusinessStatusEnum businessStatus;

    protected Date askPublicationDate;

    protected Date creationDate;

    public BusinessDTO() {
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getAskPublicationDate() {
        return askPublicationDate;
    }

    public void setAskPublicationDate(Date askPublicationDate) {
        this.askPublicationDate = askPublicationDate;
    }

    public String getVta() {
        return vta;
    }

    public void setVta(String vta) {
        this.vta = vta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessStatusEnum getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(BusinessStatusEnum businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public StoredFileDTO getLandscape() {
        return landscape;
    }

    public void setLandscape(StoredFileDTO landscape) {
        this.landscape = landscape;
    }

    public Integer getTotalFollowers() {
        return totalFollowers;
    }

    public void setTotalFollowers(Integer totalFollowers) {
        this.totalFollowers = totalFollowers;
    }

    public StoredFileDTO getIllustration() {
        return illustration;
    }

    public void setIllustration(StoredFileDTO illustration) {
        this.illustration = illustration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BusinessDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", illustration=" + illustration +
                '}';
    }
}
