package com.dev.app.microservicecoursemanagement.intercomm;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-service")
public interface UserClient {

	 @RequestMapping(method = RequestMethod.POST, value = "/service/names", consumes = "application/json")
	 List<String> getUserNames(@RequestBody List<Long> userIdList);
}
