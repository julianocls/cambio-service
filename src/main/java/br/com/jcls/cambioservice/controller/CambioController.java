package br.com.jcls.cambioservice.controller;

import br.com.jcls.cambioservice.model.Cambio;
import br.com.jcls.cambioservice.service.CambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("cambio-service")
public class CambioController {

    @Autowired
    private CambioService cambioService;

    @GetMapping("/{amount}/{from}/{to}")
    public Cambio getCambio(
            @PathVariable("amount")BigDecimal amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to
            ) {

        var cambio = cambioService.findByFromAndTo(from, to, amount);

        return cambio;
    }

}
