package br.com.jcls.cambioservice.repository;

import br.com.jcls.cambioservice.model.Cambio;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioRepository extends JpaRepository<Cambio, Long> {
    @Cacheable(cacheNames = "Cambio")
    Cambio findByFromAndTo(String from, String to);

}
