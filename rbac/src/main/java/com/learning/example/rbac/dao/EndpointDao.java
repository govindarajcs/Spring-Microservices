package com.learning.example.rbac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.example.rbac.entity.Endpoint;

public interface EndpointDao extends JpaRepository<Endpoint, Integer> {
	
	public Endpoint getByHttpMethodAndSuffixPath(String httpName, String endpointName);

}
