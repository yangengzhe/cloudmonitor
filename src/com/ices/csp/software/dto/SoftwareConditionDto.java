/**
 * 
 */
package com.ices.csp.software.dto;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.cspframework.core.dto.annotation.DtoClass;

import com.ices.csp.software.domain.Software;


/**
 * 数据传输类-软件查询条件
 * @author Fanchao Meng
 *
 */
@DtoClass(entities={Software.class})
public class SoftwareConditionDto {
	private Integer id;
	private String code;
	private String name;
	private Integer typeId;

	public SoftwareConditionDto(){	}
	public SoftwareConditionDto(String json){	}
	public static SoftwareConditionDto valueOf(String json)
			throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		SoftwareConditionDto condition = (SoftwareConditionDto)mapper.readValue(json, SoftwareConditionDto.class);
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
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	
}
