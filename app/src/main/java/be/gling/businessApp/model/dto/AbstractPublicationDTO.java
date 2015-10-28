package be.gling.businessApp.model.dto;


import android.graphics.Bitmap;

import be.gling.businessApp.R;
import be.gling.businessApp.model.PublicationTypeEnum;
import be.gling.businessApp.model.dto.technical.DTO;
import be.gling.businessApp.model.util.annotation.NotNull;
import be.gling.businessApp.model.util.annotation.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by florian on 5/06/15.
 */
public abstract class AbstractPublicationDTO extends DTO implements Comparable<AbstractPublicationDTO> {


    protected Long id;

    @NotNull
    @Size(min = 2,max = 100,message = R.string.form_publication_error_title)
    protected String title;

    @Size(min = 0,max = 1000,message = R.string.form_publication_error_description)
    protected String description;

    protected Date startDate;

    protected Date endDate;

    protected Long distance;

    protected String businessName;

    protected StoredFileDTO businessIllustration;

    protected Long businessId;

    protected Boolean following;

    protected Integer totalFollowers;

    protected PublicationTypeEnum type;

    protected List<StoredFileDTO> pictures = new ArrayList<StoredFileDTO>();

    protected CustomerInterestDTO interest;



    public AbstractPublicationDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public StoredFileDTO getBusinessIllustration() {
        return businessIllustration;
    }

    public void setBusinessIllustration(StoredFileDTO businessIllustration) {
        this.businessIllustration = businessIllustration;
    }

    public PublicationTypeEnum getType() {
        return type;
    }

    public void setType(PublicationTypeEnum type) {
        this.type = type;
    }

    public Integer getTotalFollowers() {
        return totalFollowers;
    }

    public void setTotalFollowers(Integer totalFollowers) {
        this.totalFollowers = totalFollowers;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Boolean getFollowing() {
        return following;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<StoredFileDTO> getPictures() {
        return pictures;
    }

    public void setPictures(List<StoredFileDTO> pictures) {
        this.pictures = pictures;
    }

    public void setFollowing(Boolean following) {
        this.following = following;
    }

    public CustomerInterestDTO getInterest() {
        return interest;
    }

    public void setInterest(CustomerInterestDTO interest) {
        this.interest = interest;
    }

    @Override
    public int compareTo(AbstractPublicationDTO o) {
//        if (this.getDistance() == null || this.getDistance().compareTo(o.getDistance()) == 0) {
            return o.startDate.compareTo(this.startDate);
//        }
//        return this.getDistance().compareTo(o.getDistance());
    }

    @Override
    public String toString() {
        return "AbstractPublicationDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", distance=" + distance +
                ", businessName='" + businessName + '\'' +
                '}';
    }
}
