package be.gling.businessApp.model.util.externalRequest;

import be.gling.businessApp.model.dto.BusinessNotificationDTO;
import be.gling.businessApp.model.dto.ListPublicationDTO;
import be.gling.businessApp.model.dto.LoginSuccessDTO;
import be.gling.businessApp.model.dto.PromotionDTO;
import be.gling.businessApp.model.dto.ResultDTO;
import be.gling.businessApp.model.dto.post.ForgotPasswordDTO;
import be.gling.businessApp.model.dto.post.LoginDTO;
import be.gling.businessApp.model.dto.technical.DTO;

/**
 * Created by florian on 11/11/14.
 * List the aviable request from the server
 */
public enum RequestEnum {

    //login, etc..
    LOGIN(
            RequestType.POST, false, "rest/login_for_business", LoginDTO.class, LoginSuccessDTO.class),
    LOAD_DATA(
            RequestType.GET, true, "rest/business_data", null, LoginSuccessDTO.class),
    LOGIN_FACEBOOK(
            RequestType.GET, false, "rest/login_for_business/facebook/:token", null, LoginSuccessDTO.class),
    FORGOT_PASSWORD(
            RequestType.PUT, false, "rest/forgot/password", ForgotPasswordDTO.class, ResultDTO.class),

    //publication
    LOAD_ALL_PUBLICATION(
            RequestType.GET, true, "rest/search/publication/business/:businessId/:page", null, ListPublicationDTO.class),
    CREATE_PROMOTION(
            RequestType.POST, true, "rest/promotion", PromotionDTO.class, PromotionDTO.class),
    CREATE_NOTIFICATION(
            RequestType.POST, true, "rest/businessNotification", BusinessNotificationDTO.class, BusinessNotificationDTO.class);


    private final RequestType requestType;
    private final boolean needAuthentication;
    private final String target;
    private final Class<? extends DTO> sentDTO;
    private final Class<? extends DTO> receivedDTO;

    private RequestEnum(RequestType requestType,
                        boolean needAuthentication,
                        String target,
                        Class<? extends DTO> sentDTO,
                        Class<? extends DTO> receivedDTO) {

        this.requestType = requestType;
        this.needAuthentication = needAuthentication;
        this.target = target;
        this.sentDTO = sentDTO;
        this.receivedDTO = receivedDTO;
    }


    public String getTarget() {
        return target;
    }

    public Class<? extends DTO> getSentDTO() {
        return sentDTO;
    }

    public Class<? extends DTO> getReceivedDTO() {
        return receivedDTO;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public boolean needAuthentication() {
        return needAuthentication;
    }

    public static enum RequestType {
        GET, POST, PUT, DELETE
    }
}
