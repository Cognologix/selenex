package com.cadent.test.service.cmn;

import com.cadent.test.annotation.PageFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JsonParserService {

    private static final Logger LOGGER = LoggerFactory.getLogger (JsonParserService.class);

    private JSONObject jsonObject;
    private JSONArray jsonArray;




    public String getJsonObject(final String jsonString){

            String result = null;

        try {
            jsonObject = new JSONObject (jsonString);
            result = jsonObject.get (jsonString).toString ();

        } catch (JSONException e) {
            LOGGER.info (e.getMessage ());
        }

        return  result;
    }

    public JSONArray getJsonArray(final String searchString){

        try {
            jsonArray = new JSONArray (searchString);


        } catch (JSONException e) {
            LOGGER.info (e.getMessage ());
        }

        return  jsonArray;
    }

}
