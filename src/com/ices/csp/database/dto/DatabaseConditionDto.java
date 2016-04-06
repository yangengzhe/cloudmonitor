/**
 * 
 */
package com.ices.csp.database.dto;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.cspframework.core.dto.annotation.DtoClass;

import com.ices.csp.database.domain.Database;


/**
 * @author MFC
 *
 */
@DtoClass(entities={Database.class})
public class DatabaseConditionDto {
	private Integer id;
	private String name;
	private String ip;
	public DatabaseConditionDto(){}
	public DatabaseConditionDto(String json){}
	public static DatabaseConditionDto valueOf(String json)
			throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		DatabaseConditionDto condition = (DatabaseConditionDto)mapper.readValue(json, DatabaseConditionDto.class);
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	

}
