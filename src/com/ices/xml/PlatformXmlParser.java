package com.ices.xml;

import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.cspframework.common.Global;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.manage.component.domain.Component;
import org.cspframework.manage.component.service.ComponentService;
import org.cspframework.manage.entermenu.domain.EnterMenu;
import org.cspframework.manage.enterprise.domain.Enterprise;
import org.cspframework.manage.enterprise.service.EnterpriseService;
import org.cspframework.manage.enumitem.ComponentState;
import org.cspframework.manage.enumitem.EnterpriseState;
import org.cspframework.manage.enumitem.ServiceType;
import org.cspframework.manage.enumitem.SexItem;
import org.cspframework.manage.enumitem.UserState;
import org.cspframework.manage.enumitem.UserType;
import org.cspframework.manage.group.domain.Group;
import org.cspframework.manage.group.service.GroupService;
import org.cspframework.manage.menu.domain.Menu;
import org.cspframework.manage.menu.service.MenuService;
import org.cspframework.manage.role.domain.Role;
import org.cspframework.manage.role.service.RoleService;
import org.cspframework.manage.component.domain.Service;
import org.cspframework.manage.user.domain.User;
import org.cspframework.manage.user.service.UserService;
import org.cspframework.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import com.ices.xml.jaxb.JAXBContextWrapper;
import com.ices.xml.metadata.XmlComponent;
import com.ices.xml.metadata.XmlComponents;
import com.ices.xml.metadata.XmlEnterprise;
import com.ices.xml.metadata.XmlEnterprises;
import com.ices.xml.metadata.XmlGroup;
import com.ices.xml.metadata.XmlGroups;
import com.ices.xml.metadata.XmlMenu;
import com.ices.xml.metadata.XmlMenus;
import com.ices.xml.metadata.XmlRootMenu;
import com.ices.xml.metadata.XmlService;
import com.ices.xml.metadata.XmlUser;
import com.ices.xml.metadata.XmlUserGroup;
import com.ices.xml.metadata.XmlUsers;
import com.ices.xml.xml.PlatformInit;

@Path("xmlparse")
@Controller
public class PlatformXmlParser {
	@Autowired
	private JAXBContextWrapper jaxb;

	@Autowired
	private MenuService menuService;

	@Autowired
	private ComponentService componentService;

	@Autowired
	private EnterpriseService enterpriseService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private JdbcTemplate jt;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("parsePost")
	public Map<String, String> parsePost(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		Map<String, String> result = new HashMap<String, String>();
		String init_username=request.getParameter("init_username");
		String init_password=request.getParameter("init_password");
		if(userService.hasUser(init_username, init_password)){
			PlatformInit pi = null;
			try {
				InputStream inputStream = getInputStream(request);
				if(inputStream!=null){
					pi = jaxb.unmarshal(inputStream, PlatformInit.class);
					parseXml(pi);
				}
				result.put("message", "true");
			} catch (JAXBException e) {
				result.put("message", "false");
				e.printStackTrace();
			}
		}else{
			result.put("message", "nosuchuser");
		}
		return result;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("unParsePost/{init_username}/{init_password}")
	public Map<String,String> unParsePost(@PathParam("init_username")String init_username,@PathParam("init_password")String init_password) {
		Map<String, String> result = new HashMap<String, String>();
		if(userService.hasUser(init_username, init_password)){
			try {
				clearAllPlatformDatas();
				result.put("message", "true");
			} catch (Exception e) {
				result.put("message", "false");
				e.printStackTrace();
			}
		}else{
			result.put("message", "nosuchuser");
		}
		return result;
	}
	
	//清除平台数据
	private void clearAllPlatformDatas() throws Exception {
		String a = Global.WEBROOT_PATH;
		List<String> sqls = loadSql(a+"WEB-INF/classes/platform_init.sql");
		for (String string : sqls) {
			jt.execute(string);
		}
	}
	/** 
     * 读取 SQL 文件，获取 SQL 语句 
     * @param sqlFile SQL 脚本文件 
     * @return List<sql> 返回所有 SQL 语句的 List 
     * @throws Exception 
     */  
    private List<String> loadSql(String sqlFile) throws Exception {  
        List<String> sqlList = new ArrayList<String>();  
        
        try {  
            InputStream sqlFileIn = new FileInputStream(sqlFile);  
  
            StringBuffer sqlSb = new StringBuffer();  
            byte[] buff = new byte[1024];  
            int byteRead = 0;  
            while ((byteRead = sqlFileIn.read(buff)) != -1) {  
                sqlSb.append(new String(buff, 0, byteRead));  
            }  
  
            // Windows 下换行是 /r/n, Linux 下是 /n  
            String[] sqlArr = sqlSb.toString().split(";\r\n");  
            for (int i = 0; i < sqlArr.length; i++) {  
                String sql = sqlArr[i].replaceAll("--.*", "").trim();  
                if (!sql.equals("")) {
                    sqlList.add(sql);  
                }
            }
            return sqlList;
        } catch (Exception ex) {  
            throw new Exception(ex.getMessage());  
        }  
    }   
	

	@Deprecated
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("unParsePostDeprecated")
	public String unParsePostDeprecated(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		PlatformInit pi = null;
		try {
			InputStream inputStream = getInputStream(request);
			if(inputStream!=null){
				pi = jaxb.unmarshal(inputStream, PlatformInit.class);
				unParseXml(pi);
			}
			return "true";
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return "false";
	}
	
	/**
	 * 读取初始化文件
	 * @param request
	 * @return
	 */
	private InputStream getInputStream(HttpServletRequest request){
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(3 * 1024 * 1024);
		try {
			List<?> items = upload.parseRequest(request);
			Iterator<?> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					InputStream uploadedStream = item.getInputStream();
					return uploadedStream;
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	// 将该初始化文件中相应的数据删除
	@Deprecated
	private void unParseXml(PlatformInit platformInit) throws BusinessException{
		if (platformInit != null) {
			XmlComponents xmlComponents = platformInit.getComponents();
			XmlMenus xmlMenus = platformInit.getMenus();
			XmlEnterprises xmlEnterprises = platformInit.getEnterprises();

			// menu
			if (xmlMenus != null) {
				List<XmlRootMenu> xmlRootMenuList = xmlMenus.getRootmenus();
				if (xmlRootMenuList != null && xmlRootMenuList.size() > 0) {
					for (XmlRootMenu xmlRootMenu : xmlRootMenuList) {
						List<XmlMenu> xmlMenuList = xmlRootMenu.getMenus();
						if (xmlMenuList != null && xmlMenuList.size() > 0) {
							for (XmlMenu xmlMenu : xmlMenuList) {
								Menu menu2 = menuService.findByIdSeq(xmlMenu.getIdSeq());
								if(menu2!=null){
									menuService.deleteById(menu2.getId());
								}
							}
						}
						Menu menu = menuService.findByIdSeq(xmlRootMenu.getIdSeq());
						if(menu!=null){
							menuService.deleteById(menu.getId());
						}
					}
				}
			}
			// components
			if (xmlComponents != null) {
				List<XmlComponent> xmlComponentList = xmlComponents.getComponents();
				if (xmlComponentList != null && xmlComponentList.size() > 0) {
					for (XmlComponent xmlComponent : xmlComponentList) {
						Component component = componentService.findComponentByCode(xmlComponent.getComponentCode());
						if(component!=null){
							componentService.deleteById(component.getId());
						}
					}
				}
			}

			// enterprise
			if (xmlEnterprises != null) {
				List<XmlEnterprise> xmlEnterPriseList = xmlEnterprises.getEnterprises();
				if (xmlEnterPriseList != null && xmlEnterPriseList.size() > 0) {
					for (XmlEnterprise xmlEnterprise : xmlEnterPriseList) {
						Enterprise enterprise = enterpriseService.findByCode(xmlEnterprise.getEnterpriseCode());

						XmlGroups xmlGroups = xmlEnterprise.getGroups();
						if (xmlGroups != null) {
							List<XmlGroup> xmlGroupList = xmlGroups.getGroups();
							if (xmlGroupList != null && xmlGroupList.size() > 0) {
								for (XmlGroup xmlGroup : xmlGroupList) {
									Group group = groupService.findByName(xmlGroup.getGroupName());
									if(group!=null){
										groupService.deleteById(group.getId());
									}
								}
							}
						}

						XmlUsers xmlUsers = xmlEnterprise.getUsers();
						if (xmlUsers != null) {
							List<XmlUser> xmlUserList = xmlUsers.getUsers();
							if (xmlUserList != null && xmlUserList.size() > 0) {
								for (XmlUser xmlUser : xmlUserList) {
									User user = userService.findUserByLoginName(xmlUser.getUserName());
									if(user!=null){
										userService.deleteById(user.getId());
									}
								}
							}
						}
						
						if(enterprise!=null){
							enterpriseService.deleteById(enterprise.getId());
						}
					}
				}
			}
		}
	}

	// 根据xml进行平台初始化
	private void parseXml(PlatformInit platformInit) {
		if (platformInit != null) {
			XmlComponents xmlComponents = platformInit.getComponents();
			XmlMenus xmlMenus = platformInit.getMenus();
			XmlEnterprises xmlEnterprises = platformInit.getEnterprises();

			Map<String, Component> componentMap = new HashMap<String, Component>();
			Map<String, Service> serviceMap = new HashMap<String, Service>();
			Map<String, Menu> menuMap = new HashMap<String, Menu>();
			// components
			if (xmlComponents != null) {
				List<XmlComponent> xmlComponentList = xmlComponents.getComponents();
				if (xmlComponentList != null && xmlComponentList.size() > 0) {
					for (XmlComponent xmlComponent : xmlComponentList) {
						Component component = new Component();
						component.setCode(xmlComponent.getComponentCode());
						component.setBizUrl(xmlComponent.getBizUrl());
						component.setDesc(xmlComponent.getDescription());
						component.setDeveloper(xmlComponent.getDeveloper());
						component.setName(xmlComponent.getComponentName());
						component.setState(ComponentState.getMatchByOrdinal(Integer.valueOf(xmlComponent.getState())));
						component.setWebUrl(xmlComponent.getWebUrl());

						Set<Service> services = new HashSet<Service>();
						List<XmlService> xmlServiceList = xmlComponent.getServices();
						if (xmlServiceList != null && xmlServiceList.size() > 0) {
							for (XmlService xmlService : xmlServiceList) {
								Service service = new Service();
								service.setName(xmlService.getServiceName());
								service.setCode(xmlService.getServiceCode());
								service.setDesc(xmlService.getDescription());
								service.setServiceUrl(xmlService.getServiceUrl());
								service.setParameters(xmlService.getParameters());
								service.setComponent(component);
								services.add(service);// 加入到set中
								serviceMap.put(xmlService.getServiceCode(), service);// 加入到map中
							}
						}
						component.setServices(services);
						componentMap.put(component.getCode(), component);// 加入到map
						componentService.insert(component);// component插入到数据库
					}
				}
			}
			// menu
			if (xmlMenus != null) {
				List<XmlRootMenu> xmlRootMenuList = xmlMenus.getRootmenus();
				if (xmlRootMenuList != null && xmlRootMenuList.size() > 0) {
					for (XmlRootMenu xmlRootMenu : xmlRootMenuList) {
						Menu menu = new Menu();
						menu.setName(xmlRootMenu.getMenuName());
						menu.setDesc(xmlRootMenu.getDescription());
						menu.setIdSeq(xmlRootMenu.getIdSeq());
						menu.setDisplayOrder(Integer.valueOf(xmlRootMenu.getDisplayOrder()));
						menu.setLeaf("0".equals(xmlRootMenu.getLeaf()));
						String serviceName = xmlRootMenu.getServiceName();
						Service service = serviceMap.get(serviceName);
						if (service != null) {
							menu.setService(service);
						}
						menuService.insert(menu);// 插入menu
						menuMap.put(xmlRootMenu.getIdSeq(), menu);// 加入到map

						List<XmlMenu> xmlMenuList = xmlRootMenu.getMenus();
						if (xmlMenuList != null && xmlMenuList.size() > 0) {
							for (XmlMenu xmlMenu : xmlMenuList) {
								Menu menu2 = new Menu();
								menu2.setName(xmlMenu.getMenuName());
								menu2.setDesc(xmlMenu.getDescription());
								menu2.setIdSeq(xmlMenu.getIdSeq());
								menu2.setDisplayOrder(Integer.valueOf(xmlMenu.getDisplayOrder()));
								menu2.setLeaf("0".equals(xmlMenu.getLeaf()));
								menu2.setParent(menu);// 父菜单
								Service service2 = serviceMap.get(xmlMenu.getServiceCode());
								if (service2 != null) {
									menu2.setService(service2);
								}
								menuService.insert(menu2);// 插入menu
								menuMap.put(xmlMenu.getIdSeq(), menu2);// 加入到map
							}
						}
					}
				}
			}

			// enterprise
			if (xmlEnterprises != null) {
				List<XmlEnterprise> xmlEnterPriseList = xmlEnterprises.getEnterprises();
				if (xmlEnterPriseList != null && xmlEnterPriseList.size() > 0) {
					for (XmlEnterprise xmlEnterprise : xmlEnterPriseList) {
						Enterprise enterprise = new Enterprise();
						enterprise.setCode(xmlEnterprise.getEnterpriseCode());
						enterprise.setName(xmlEnterprise.getEnterpriseName());
						enterprise.setAbbreviation(xmlEnterprise.getAbbreviation());
						enterprise.setState(EnterpriseState.getMatchByOrdinal(Integer.valueOf(xmlEnterprise.getState())));
						enterprise.setServiceType(ServiceType.getMatchByOrdinal(Integer.valueOf(xmlEnterprise.getServiceType())));

						Set<Group> groups = new HashSet<Group>();
						Map<String, Group> groupMap = new HashMap<String, Group>();
						Set<User> users = new HashSet<User>();
						Set<EnterMenu> enterMenus = new HashSet<EnterMenu>();

						XmlGroups xmlGroups = xmlEnterprise.getGroups();
						XmlUsers xmlUsers = xmlEnterprise.getUsers();
						// groups
						if (xmlGroups != null) {
							List<XmlGroup> xmlGroupList = xmlGroups.getGroups();
							if (xmlGroupList != null && xmlGroupList.size() > 0) {
								for (XmlGroup xmlGroup : xmlGroupList) {
									Group group = new Group();
									group.setDesc(xmlGroup.getDescription());
									group.setName(xmlGroup.getGroupName());

									Set<EnterMenu> enterMenus2 = new HashSet<EnterMenu>();// 待存入group的menus
									XmlMenus xmlMenus2 = xmlGroup.getMenus();
									if (xmlMenus2 != null) {
										List<XmlRootMenu> xmlRootMenuList = xmlMenus2.getRootmenus();
										if (xmlRootMenuList != null && xmlRootMenuList.size() > 0) {
											for (XmlRootMenu xmlRootMenu2 : xmlRootMenuList) {
												Menu menu = menuMap.get(xmlRootMenu2.getIdSeq());
												if (menu != null) {
													EnterMenu enterMenu = new EnterMenu();
													enterMenu.setDisplayOrder(menu.getDisplayOrder());
													enterMenu.setEnterprise(enterprise);
													enterMenu.setMenu(menu);
													if (enterMenu.getGroups() == null) {
														enterMenu.setGroups(new HashSet<Group>());
													}
													enterMenu.getGroups().add(group);
													// enterMenuService.insert(enterMenu);//插入到数据库
													enterMenus2.add(enterMenu);
													enterMenus.add(enterMenu);
												}

												List<XmlMenu> xmlMenuList = xmlRootMenu2.getMenus();
												if (xmlMenuList != null && xmlMenuList.size() > 0) {
													for (XmlMenu xmlMenu2 : xmlMenuList) {
														Menu menu2 = menuMap.get(xmlMenu2.getIdSeq());
														if (menu2 != null) {
															EnterMenu enterMenu2 = new EnterMenu();
															enterMenu2.setDisplayOrder(menu2.getDisplayOrder());
															enterMenu2.setEnterprise(enterprise);
															enterMenu2.setMenu(menu2);
															if (enterMenu2.getGroups() == null) {
																enterMenu2.setGroups(new HashSet<Group>());
															}
															enterMenu2.getGroups().add(group);

															// enterMenuService.insert(enterMenu2);//插入到数据库
															enterMenus2.add(enterMenu2);
															enterMenus.add(enterMenu2);
														}
													}
												}
											}
										}
									}
									group.setEnterMenus(enterMenus2);
									group.setEnterprise(enterprise);
									List<Role> roleList = roleService.findAllRoles();
									Set<Role> roles = new HashSet<Role>();
									for (Role role : roleList) {
										roles.add(role);
									}
									group.setRoles(roles);

									groups.add(group);// 加入到groups
									groupMap.put(group.getName(), group);
									// groupService.insert(group);//插入到数据库
								}
							}
						}

						// users
						if (xmlUsers != null) {
							List<XmlUser> xmlUserList = xmlUsers.getUsers();
							if (xmlUserList != null && xmlUserList.size() > 0) {
								for (XmlUser xmlUser : xmlUserList) {
									User user = new User();
									user.setEnterprise(enterprise);
									user.setGroups(groups);
									user.setName(xmlUser.getName());
									user.setSex(SexItem.getMatchByOrdinal(Integer.valueOf(xmlUser.getSex())));
									user.setTheme(xmlUser.getTheme());
									user.setPassword(MD5Utils.encode(xmlUser.getPassword()));
									user.setLoginName(xmlUser.getUserName());
									user.setState(UserState.getMatchByOrdinal(Integer.valueOf(xmlUser.getState())));
									user.setType(UserType.getMatchByOrdinal(Integer.valueOf(xmlUser.getUserType())));
									
									//性别
									user.setSex(SexItem.MALE);
									//创建时间
									user.setInsertTime(new Date());

									Set<Group> groups2 = new HashSet<Group>();
									List<XmlUserGroup> xmlUserGroupList = xmlUser.getUserGroups();
									if (xmlUserGroupList != null && xmlUserGroupList.size() > 0) {
										for (XmlUserGroup xmlUserGroup : xmlUserGroupList) {
											Group group = groupMap.get(xmlUserGroup.getGroupName());
											if (group != null) {
												groups2.add(group);
											}
										}
									}
									user.setGroups(groups2);
									users.add(user);
									// userService.insert(user);//插入到数据库

								}
							}
						}
						enterprise.setEnterpriceMenus(enterMenus);
						enterprise.setGroups(groups);
						enterprise.setUsers(users);
						enterpriseService.insert(enterprise);// 插入到数据库
					}
				}
			}
		}
	}
}
