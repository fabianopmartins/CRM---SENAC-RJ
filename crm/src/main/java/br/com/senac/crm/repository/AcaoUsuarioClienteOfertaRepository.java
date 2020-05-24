package br.com.senac.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.senac.crm.models.AcaoUsuarioClienteOferta;

@Repository
public interface AcaoUsuarioClienteOfertaRepository extends JpaRepository<AcaoUsuarioClienteOferta, Integer> {
}