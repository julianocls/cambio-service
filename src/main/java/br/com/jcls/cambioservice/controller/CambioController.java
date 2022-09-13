package br.com.jcls.cambioservice.controller;

import br.com.jcls.avro.BookletChargeMigration;
import br.com.jcls.avro.PaymentDate;
import br.com.jcls.avro.Status;
import br.com.jcls.cambioservice.model.Cambio;
import br.com.jcls.cambioservice.service.CambioService;
import br.com.jcls.cambioservice.service.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequestMapping("cambio-service")
public class CambioController {

    @Autowired
    private CambioService cambioService;

    @Autowired
    private TopicProducer topicProducer;

    @GetMapping("/{amount}/{from}/{to}")
    public Cambio getCambio(
            @PathVariable("amount")BigDecimal amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to
            ) {

        var cambio = cambioService.findByFromAndTo(from, to, amount);

        var map = new HashMap<String, PaymentDate>();
        map.put("WAITING", new PaymentDate("Thu, 24 Aug 2022 01:45:15 GMT"));
        map.put("PAID", new PaymentDate("Thu, 25 Aug 2022 01:45:15 GMT"));


        var paymentHistory = BookletChargeMigration.newBuilder().setPaymentHistory(map);
        var booklet = BookletChargeMigration.newBuilder()
                .setAccountId("54fg84g2sfd213a")
                .setId("5sd4af6a5sd4fa")
                .setLinkId("d56a4f")
                .setStatus(Status.ACTIVE)
                .setPaymentHistory(map).build();

        topicProducer.send(booklet);

        return cambio;
    }

}
