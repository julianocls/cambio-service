package br.com.jcls.cambioservice.service;

import br.com.jcls.avro.BookletChargeMigration;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Properties;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {

    private final KafkaTemplate<Integer, BookletChargeMigration> kafkaTemplate;

    public void send(BookletChargeMigration message) {
        String topicName = "topico.avro.teste";

        ListenableFuture<SendResult<Integer, BookletChargeMigration>> future = kafkaTemplate.send(topicName, message);

        log.info("Payload enviado: {}", message);
        kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<Integer, BookletChargeMigration>>() {
            @Override
            public void onSuccess(SendResult<Integer, BookletChargeMigration> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }

}
