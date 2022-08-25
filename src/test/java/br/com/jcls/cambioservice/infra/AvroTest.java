package br.com.jcls.cambioservice.infra;

import br.com.jcls.avro.BookletChargeMigration;
import br.com.jcls.avro.PaymentDate;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AvroTest {


    @Test
    void testLoadScheWithMoreOfOneStatus() {

        var map = new HashMap<String, PaymentDate>();
        map.put("WAITING", new PaymentDate("Thu, 24 Aug 2022 01:45:15 GMT"));
        map.put("PAID", new PaymentDate("Thu, 25 Aug 2022 01:45:15 GMT"));

        var paymentHistory = BookletChargeMigration.newBuilder().setPaymentHistory(map);

        var booklet = BookletChargeMigration.newBuilder()
                .setAccountId("54fg84g2sfd213a")
                .setId("5sd4af6a5sd4fa")
                .setLinkId("d56a4f")
                .sets
                .setPaymentHistory(map);

        assertNotNull(booklet);
    }

    @Test
    void testLoadSchewWithoutStatus() {

        var map = new HashMap<String, PaymentDate>();

        var paymentHistory = BookletChargeMigration.newBuilder().setPaymentHistory(map);

        var booklet = BookletChargeMigration.newBuilder()
                .setAccountId("54fg84g2sfd213a")
                .setId("5sd4af6a5sd4fa")
                .setLinkId("d56a4f")
                .setPaymentHistory(map);

        assertNotNull(booklet);
    }

}
