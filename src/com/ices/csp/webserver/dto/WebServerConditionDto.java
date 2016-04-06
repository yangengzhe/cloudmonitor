/**
 * 
 */
package com.ices.csp.webserver.dto;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.cspframework.core.dto.annotation.DtoClass;

import com.ices.csp.webserver.domain.WebServer;



/**
 * @author MFC
 *
 */
@DtoClass(entities={WebServer.class})
public class WebServerConditionDto {
	private Integer id;
	private String name;
	private String state;
	private String ip;
	public WebServerConditionDto(){}
	public WebServerConditionDto(String json){}
	public static WebServerConditionDto valueOf(String json)
			throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		WebServerConditionDto condition = (WebServerConditionDto)mapper.readValue(json, WebServerConditionDto.class);
		return condition;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	

}
