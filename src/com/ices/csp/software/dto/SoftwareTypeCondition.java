/**
 * 
 */
package com.ices.csp.software.dto;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.cspframework.core.dto.annotation.DtoClass;

import com.ices.csp.software.domain.SoftwareType;

/**
 * @author Fanchao Meng
 *
 */
@DtoClass(entities={SoftwareType.class})
public class SoftwareTypeCondition {
	private Integer id;
	private String code;
	private String name;

	public SoftwareTypeCondition(){	}
	public SoftwareTypeCondition(String json){	}
	public static SoftwareTypeCondition valueOf(String json)
			throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		SoftwareTypeCondition condition = (SoftwareTypeCondition)mapper.readValue(json, SoftwareTypeCondition.class);
		return condition;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
