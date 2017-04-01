package com.demo.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.service.TestService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@EnableAsync
@RestController  
public class ControllerTest {
	public static Logger LOG = LoggerFactory.getLogger(ControllerTest.class);
	@Autowired
	private TestService testService;
	
	@ApiOperation(value="get测试", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息") 
	@RequestMapping(value = "/get", method = RequestMethod.GET)  
    public Map loginByGet(
    		@ApiParam(name = "name",value = "姓名",required = true)
    		@RequestParam(value = "name", required = true) String name, 
    		@ApiParam(name = "pwd",value = "密码",required = true)
            @RequestParam(value = "pwd", required = true) String pwd) { 
		Map result = new HashMap();
		result.put("data", testService.queryById(1));
		result.put("pwd", pwd);
        return result;  
    }  
  
	@ApiOperation("post测试") 
    @RequestMapping(value = "/post", method = RequestMethod.POST)  
    public Map loginByPost(@RequestParam(value = "name", required = true) String name,  
            @RequestParam(value = "pwd", required = true) String pwd) {  
    	
    	Map result = new HashMap();
		result.put("name", name);
		result.put("pwd", pwd);
        return result;  
    }  
    
    @RequestMapping(value = "/get666", method = RequestMethod.GET)  
    public String endcode() { 
        return "ok";  
    }  
    
    @ApiOperation(value="get菜单", notes="根据json渲染菜单") 
	@RequestMapping(value = "/getMenu", method = RequestMethod.GET)  
    public List getMenu() { 
    	List result = new ArrayList();
    	result =  testService.menuConf();
        return result;  
    }  
}
