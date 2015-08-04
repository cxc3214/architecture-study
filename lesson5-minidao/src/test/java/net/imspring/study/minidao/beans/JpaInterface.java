package net.imspring.study.minidao.beans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface JpaInterface extends JpaRepository<ModelA, Integer>, JpaSpecificationExecutor<ModelA>{
	
}
