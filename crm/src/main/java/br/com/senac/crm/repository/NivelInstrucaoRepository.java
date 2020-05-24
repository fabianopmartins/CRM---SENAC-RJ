package br.com.senac.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.crm.models.NivelInstrucao;

@Repository
public interface NivelInstrucaoRepository extends JpaRepository<NivelInstrucao, Integer> {
	
}