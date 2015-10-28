package be.gling.businessApp.model.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

import be.gling.businessApp.model.dto.AbstractPublicationDTO;
import be.gling.businessApp.model.dto.BusinessNotificationDTO;
import be.gling.businessApp.model.dto.PromotionDTO;
import be.gling.businessApp.model.dto.technical.ExceptionDTO;

/**
 * Created by florian on 10/10/15.
 */
public class PublicationAdapter implements JsonSerializer<AbstractPublicationDTO>, JsonDeserializer<AbstractPublicationDTO> {

    @Override
    public AbstractPublicationDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        AbstractPublicationDTO dto;

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });

        Gson gson = gsonBuilder.create();


        JsonObject jsonObject = json.getAsJsonObject();

        if (jsonObject.get("__type").toString().contains("BusinessNotificationDTO")) {
            dto = gson.fromJson(json, BusinessNotificationDTO.class);

        } else {
            dto = gson.fromJson(json, PromotionDTO.class);
        }


        return dto;
    }

    @Override
    public JsonElement serialize(AbstractPublicationDTO src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }

    private Long getLong(JsonObject jsonObject, String name) {
        JsonElement jsonElement = jsonObject.get(name);
        if (jsonElement != null) {
            return jsonElement.getAsLong();
        }
        return null;
    }
}
