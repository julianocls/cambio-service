package br.com.jcls.cambioservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {

    private final KafkaTemplate<Integer, SpecificRecord> kafkaTemplate;

    public void send(SpecificRecord message, String topicName) {
        //ListenableFuture<SendResult<Integer, SpecificRecord>> future = kafkaTemplate.send(topicName, message);

        log.info("Payload enviado: {}", message);
        kafkaTemplate.send(topicName, message);

        /*future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<Integer, SpecificRecord> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });*/
    }

}
