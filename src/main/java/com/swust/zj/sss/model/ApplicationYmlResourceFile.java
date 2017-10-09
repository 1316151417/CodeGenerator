package com.swust.zj.sss.model;

import com.swust.zj.template.ResourceFile;

/**
* @author 周杰
* @time 2017年10月9日 上午11:49:03
*/
public class ApplicationYmlResourceFile extends ResourceFile {
	private static final String FILE_NAME = "application.yml";
	public ApplicationYmlResourceFile(String fileDir) {
		super(fileDir, FILE_NAME);
		String resource = 
				"server:\r\n" + 
				"  port: 8080\r\n" + 
				"  context-path: /\r\n" + 
				"spring:\r\n" + 
				"  datasource:\r\n" + 
				"    driver-class-name: com.mysql.jdbc.Driver\r\n" + 
				"    url: jdbc:mysql://127.0.0.1:3306/test\r\n" + 
				"    username: root\r\n" + 
				"    password: 123456\r\n" + 
				"    tomcat:\r\n" + 
				"      initial-size: 5\r\n" + 
				"      max-active: 30\r\n" + 
				"  jpa:\r\n" + 
				"    hibernate:\r\n" + 
				"      ddl-auto: update\r\n" + 
				"    show-sql: true";
		init(resource);
	}
}
