package com.swust.zj.sss.model;

import com.swust.zj.template.ResourceFile;

/**
* @author 周杰
* @time 2017年10月9日 上午11:58:48
*/
public class PomXmlResourceFile extends ResourceFile {
	private static final String FILE_NAME = "pom.xml";
	public PomXmlResourceFile(String fileDir) {
		super(fileDir, FILE_NAME);
		String resource = 
				"<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\r\n" + 
				"  <modelVersion>4.0.0</modelVersion>\r\n" + 
				"  <groupId>com.swust.zj</groupId>\r\n" + 
				"  <artifactId>ProjectBySSSGenerator</artifactId>\r\n" + 
				"  <version>0.0.1-SNAPSHOT</version>\r\n" + 
				"  <packaging>jar</packaging>\r\n" + 
				"	<parent>\r\n" + 
				"		<groupId>org.springframework.boot</groupId>\r\n" + 
				"		<artifactId>spring-boot-starter-parent</artifactId>\r\n" + 
				"		<version>1.5.3.RELEASE</version>\r\n" + 
				"		<relativePath/>\r\n" + 
				"	</parent>\r\n" + 
				"	<properties>\r\n" + 
				"		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\r\n" + 
				"		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>\r\n" + 
				"		<java.version>1.8</java.version>\r\n" + 
				"	</properties>\r\n" + 
				"		<dependencies>\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>org.springframework.boot</groupId>\r\n" + 
				"			<artifactId>spring-boot-starter-web</artifactId>\r\n" + 
				"		</dependency>\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>org.springframework.boot</groupId>\r\n" + 
				"			<artifactId>spring-boot-starter-test</artifactId>\r\n" + 
				"			<scope>test</scope>\r\n" + 
				"		</dependency>\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>org.springframework.boot</groupId>\r\n" + 
				"			<artifactId>spring-boot-starter-aop</artifactId>\r\n" + 
				"		</dependency>\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>org.springframework.boot</groupId>\r\n" + 
				"			<artifactId>spring-boot-starter-data-jpa</artifactId>\r\n" + 
				"		</dependency>\r\n" + 
				"		<dependency>\r\n" + 
				"			<groupId>mysql</groupId>\r\n" + 
				"			<artifactId>mysql-connector-java</artifactId>\r\n" + 
				"		</dependency>\r\n" + 
				"	</dependencies>\r\n" + 
				"	<build>\r\n" + 
				"		<plugins>\r\n" + 
				"			<plugin>\r\n" + 
				"				<groupId>org.springframework.boot</groupId>\r\n" + 
				"				<artifactId>spring-boot-maven-plugin</artifactId>\r\n" + 
				"			</plugin>\r\n" + 
				"		</plugins>\r\n" + 
				"	</build>\r\n" + 
				"</project>";
		init(resource);
	}
}
