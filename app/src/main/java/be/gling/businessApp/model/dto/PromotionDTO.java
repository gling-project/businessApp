package be.gling.businessApp.model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import be.gling.businessApp.R;
import be.gling.businessApp.model.PublicationTypeEnum;
import be.gling.businessApp.model.util.annotation.NotNull;
import be.gling.businessApp.model.util.annotation.NumberLimit;

/**
 * Created by florian on 23/05/15.
 */
public class PromotionDTO extends AbstractPublicationDTO {

    private Double quantity;

    private Double minimalQuantity;

    private String unit;

    private Double originalPrice;

    @NotNull
    @NumberLimit(min = 0,max = 100,message = R.string.promotion_form_error_percent_limit)
    private Double offPercent;

    public PromotionDTO() {
        type= PublicationTypeEnum.PROMOTION;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getMinimalQuantity() {
        return minimalQuantity;
    }

    public void setMinimalQuantity(Double minimalQuantity) {
        this.minimalQuantity = minimalQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getOffPercent() {
        return offPercent;
    }

    public void setOffPercent(Double offPercent) {
        this.offPercent = offPercent;
    }

    @Override
    public String toString() {
        return "PromotionDTO{" +
                super.toString() +
                "quantity=" + quantity +
                ", minimalQuantity=" + minimalQuantity +
                ", unit='" + unit + '\'' +
                ", originalPrice=" + originalPrice +
                ", offPercent=" + offPercent +
                '}';
    }

}
