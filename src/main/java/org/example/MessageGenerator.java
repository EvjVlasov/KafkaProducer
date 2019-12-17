package org.example;

import org.json.JSONObject;

import java.util.Random;

public class MessageGenerator {

    private Random random = new Random();

    public String getRandomMessage() {
        int id = random.nextInt(100);
        int desc = random.nextInt(1000);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("main", "Message");
        jsonObject.put("description", desc);

        return jsonObject.toString();
    }

    public String getRandomKey() {

        int randomKey = 100 + random.nextInt(1000);

        return Integer.toString(randomKey);
    }


}
