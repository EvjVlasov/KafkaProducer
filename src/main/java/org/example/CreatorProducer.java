package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Properties;


class CreatorProducer {
    private static final Logger LOG = LogManager.getLogger(CreatorProducer.class);

    public static void main(String[] args) {
        createProducer(args);
    }

    private static void createProducer(String[] args) {
        final String TOPIC = "mytopic";

        final int QUANTITY;
        if (args.length > 0) {
            QUANTITY = Integer.parseInt(args[0]);
        } else {
            QUANTITY = 10;
        }

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        MessageGenerator message = new MessageGenerator();
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            for (int i = 0; i < QUANTITY; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, message.getRandomKey(), message.getRandomMessage());
                producer.send(record);
                System.out.println("The message has been sent to a topic: " + record.topic());
            }
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }

    }
}