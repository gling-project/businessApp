package be.gling.businessApp.model.dto;

import java.util.List;

import be.gling.businessApp.model.dto.technical.DTO;

/**
 * Created by florian on 9/10/15.
 */
public class ListPublicationDTO extends DTO{

    private List<AbstractPublicationDTO> list;

    public List<AbstractPublicationDTO> getList() {
        return list;
    }

    public void setList(List<AbstractPublicationDTO> list) {
        this.list = list;
    }
}
