package com.swust.zj.sss.model;

import java.util.LinkedList;
import java.util.List;

import com.swust.zj.template.JavaFile;

/**
* @author 周杰
* @time 2017年10月9日 上午10:22:16
*/
public class ApplicationClassFile extends JavaFile {
	private static final String CLASS_NAME = "Application";
	private static final String FILE_NAME = "Application.java";
	public ApplicationClassFile(String fileDir, String packagei) {
		super(fileDir, FILE_NAME, packagei, CLASS_TYPE, CLASS_NAME);
		List<String> imports = new LinkedList<>();
		List<String> classAnnotations = new LinkedList<>();
		List<String> extendss = new LinkedList<>();
		List<String> implementss = new LinkedList<>();
		List<JavaFile.Field> fields = new LinkedList<>();
		List<Method> methods = new LinkedList<>();
		
		//imports
		imports.add("org.springframework.boot.SpringApplication");
		imports.add("org.springframework.boot.autoconfigure.SpringBootApplication");
		imports.add("org.springframework.transaction.annotation.EnableTransactionManagement");
		
		//classAnnotations
		classAnnotations.add("SpringBootApplication");
		classAnnotations.add("EnableTransactionManagement");
		
		//extendss none
		
		//implementss none
		
		//fields none
		
		//methods
		//main
		List<String> mannotations = null;
		String mmodifier = "public static";
		String resultType = "void";
		String name = "main";
		String args = "String[] args";
		List<String> exceptions = null;
		String methodBody = buildMethod(new String[] {
				new StringBuilder()
				.append("SpringApplication.run(")
				.append(CLASS_NAME)
				.append(".class, args);")
				.toString()
		});
		Method method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		
		super.init(imports, classAnnotations, extendss, implementss, fields, methods);
	}
	private static String buildMethod(String[] lines) {
		StringBuilder method = new StringBuilder();
		if(lines != null) {
			for (String line : lines) {
				method
				.append("\t\t")
				.append(line)
				.append("\n");
			}
		}
		return method.toString();
	}
}
