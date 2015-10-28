package be.gling.businessApp.model.dto;

import be.gling.businessApp.model.PublicationTypeEnum;

/**
 * Created by florian on 1/06/15.
 */
public class BusinessNotificationDTO extends AbstractPublicationDTO  {
    public BusinessNotificationDTO() {
        type = PublicationTypeEnum.NOTIFICATION;
    }
}
