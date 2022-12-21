package com.learning.example.rbac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.example.rbac.dao.EndpointDao;
import com.learning.example.rbac.dto.EndpointDTO;
import com.learning.example.rbac.entity.Endpoint;
import com.learning.example.rbac.entity.response.EndpointResponse;

@Service
public class EndpointService {
	
	@Autowired
	EndpointDao dao;
	
	public ResponseEntity<EndpointResponse> saveServiceEndpoint(EndpointDTO dto) {
		ResponseEntity<EndpointResponse> response = null;
		try {
			if (!this.isServiceEndpointByNameAndMethodExist(dto.getHttpMethod(), dto.getEndpointName())) {
				Endpoint endpointService = new Endpoint();
				endpointService.setHttpMethod(dto.getHttpMethod());
				endpointService.setSuffixPath(dto.getEndpointName());
				Endpoint updatedEndpointService = dao.save(endpointService);
				dto.setId(updatedEndpointService.getId());
				response = new ResponseEntity<EndpointResponse>(
						new EndpointResponse(
								"Endpoint Created Successfully", 
								dto.getEndpointName(), 
								dto.getId(), 
								dto.getHttpMethod(), 
								ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(dto.getId().toString()).build().toUriString()),
						HttpStatus.CREATED);
			} else {
				response = new ResponseEntity<EndpointResponse>(new EndpointResponse("Record with endpoint "+dto.getEndpointName()+" and http method "+dto.getHttpMethod()+" already exists"), 
						HttpStatus.BAD_REQUEST);
			}
		} catch(Exception e) {
			response = new ResponseEntity<EndpointResponse>(new EndpointResponse("Endpoint Service Creation failed due to "+e.getLocalizedMessage()), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
		
	}
	
	private Boolean isServiceEndpointByNameAndMethodExist(String httpName, String endpointName) {
		Endpoint endpoint = dao.getByHttpMethodAndSuffixPath(httpName, endpointName);
		return endpoint!=null?true:false;
		
	}
	
	public ResponseEntity<EndpointResponse> getServiceEndpoint(Integer id) {
		ResponseEntity<EndpointResponse> response = null;
		try {
			Optional<Endpoint> optionalResponse = dao.findById(id);
			Endpoint endpoint = optionalResponse.isPresent()?optionalResponse.get():null;
			if (endpoint == null) {
				response = new ResponseEntity<EndpointResponse>(new EndpointResponse("Record id "+id+"doesn't exist"), HttpStatus.NOT_FOUND);
			} else {
				response = new ResponseEntity<EndpointResponse>(new EndpointResponse(endpoint.getSuffixPath(), endpoint.getId(), endpoint.getHttpMethod(), 
						ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(endpoint.getId().toString()).build().toUriString()), HttpStatus.OK);
			}
		} catch (Exception e) {
			response = new ResponseEntity<EndpointResponse> (new EndpointResponse("Cannot fetch details of teh record "+id+" due to "+e.getLocalizedMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
		
	}
	
	public ResponseEntity<List<EndpointResponse>> getAll() {
		List<EndpointResponse> endpointResponseList = new ArrayList<EndpointResponse>();
		try {
			List<Endpoint> endpointList = dao.findAll();
			for (Endpoint endpoint : endpointList) {
				endpointResponseList.add(new EndpointResponse(endpoint.getSuffixPath(), endpoint.getId(), endpoint.getHttpMethod(), 
						ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(endpoint.getId().toString()).build().toUriString()));
			}
			return new ResponseEntity<List<EndpointResponse>>(endpointResponseList, HttpStatus.ACCEPTED);
		} catch(Exception e) {
			endpointResponseList.add(new EndpointResponse("Error in getting the list of endpoint names due to "+e.getLocalizedMessage()));
			return new ResponseEntity<List<EndpointResponse>>(endpointResponseList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<EndpointResponse> deleteEndpoint(Integer id) {
		ResponseEntity<EndpointResponse> response = null;
		try {
			Optional<Endpoint> optionalResponse = dao.findById(id);
			Endpoint endpoint = optionalResponse.isPresent()?optionalResponse.get():null;
			if (endpoint!=null) {
				dao.delete(endpoint);
				response = new ResponseEntity<EndpointResponse>(new EndpointResponse("Record with endpoint "+id+" deleted successfully"), 
						HttpStatus.NO_CONTENT);
			
			} else {
				response = new ResponseEntity<EndpointResponse>(new EndpointResponse("Record with endpoint "+id+" doesn't exists"), 
						HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			response = new ResponseEntity<EndpointResponse>(new EndpointResponse("Endpoint Service deletion failed due to "+e.getLocalizedMessage()), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	public ResponseEntity<EndpointResponse> updateEndpoint(EndpointDTO dto) {
		ResponseEntity<EndpointResponse> response = null;
		try {
			Optional<Endpoint> optionalResponse = dao.findById(dto.getId());
			Endpoint endpoint = optionalResponse.isPresent()?optionalResponse.get():null;
			if (endpoint!=null) {
				endpoint.setHttpMethod(dto.getHttpMethod()!=null?dto.getHttpMethod():endpoint.getHttpMethod());
				endpoint.setSuffixPath(dto.getEndpointName()!=null?dto.getEndpointName():endpoint.getSuffixPath());
				dao.save(endpoint);
				response = new ResponseEntity<EndpointResponse>(
						new EndpointResponse(
								"Endpoint Updated Successfully", 
								dto.getEndpointName(), 
								dto.getId(), 
								dto.getHttpMethod(), 
								ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString()),
						HttpStatus.CREATED);
			} else {
				response = new ResponseEntity<EndpointResponse>(new EndpointResponse("Record with endpoint "+dto.getId()+" doesn't exists"), 
						HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			response = new ResponseEntity<EndpointResponse>(new EndpointResponse("Endpoint Service update failed due to "+e.getLocalizedMessage()), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	

}
