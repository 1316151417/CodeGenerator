package com.swust.zj.sss.model;

import java.util.LinkedList;
import java.util.List;

import com.swust.zj.template.JavaFile;

/**
* @author 周杰
* @time 2017年10月9日 上午11:20:43
*/
public class PojoClassFile extends JavaFile {
	private String model;
	public PojoClassFile(String fileDir, String mainPackage, String curPackage, String model) {
		super(fileDir, getModelClass(model).append(".java").toString(), mainPackage+"."+curPackage, CLASS_TYPE, getModelClass(model).toString());
		this.model = model;
		List<String> imports = new LinkedList<>();
		List<String> classAnnotations = new LinkedList<>();
		List<String> extendss = new LinkedList<>();
		List<String> implementss = new LinkedList<>();
		List<JavaFile.Field> fields = new LinkedList<>();
		List<Method> methods = new LinkedList<>();
		
		//imports
		imports.add("javax.persistence.Entity");
		imports.add("javax.persistence.GeneratedValue");
		imports.add("javax.persistence.Id");
		imports.add("com.fasterxml.jackson.annotation.JsonGetter");
		
		//classAnnotations
		classAnnotations.add("Entity");
		
		//extendss none
		
		//implementss none
		
		//fields
		List<String> fannotations = null;
		String fmodifier = "private";
		String ftype = "Integer";
		String fname = "id";
		String value = null;
		Field field = new Field(fannotations, fmodifier , ftype, fname, value);
		fields.add(field);
		
		//methods
		List<String> mannotations = new LinkedList<>();
		String mmodifier = "private";
		String resultType;
		String name;
		String args;
		List<String> exceptions = null;
		String methodBody = null;
		//getId
		mannotations.add("Id");
		mannotations.add("GeneratedValue");
		mannotations.add("JsonGetter");
		resultType = "Integer";
		name = "getId";
		args = null;
		methodBody = buildMethod(new String[] {
				"return id;"
		});
		Method method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//setId
		mannotations = null;
		resultType = "void";
		name = "setId";
		args = "Integer id";
		methodBody = buildMethod(new String[] {
				"this.id = id;"
		});
		method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		
		super.init(imports, classAnnotations, extendss, implementss, fields, methods);
	}
	public static StringBuilder getModelName(String model) {
		StringBuilder modelName = new StringBuilder(model.toLowerCase());
		modelName.append("_model");
		return modelName;
	}
	public static StringBuilder getModelClass(String model) {
		StringBuilder modelClass = new StringBuilder();
		modelClass.append((model.charAt(0)+"").toUpperCase());
		modelClass.append(model.toLowerCase().substring(1));
		return modelClass;
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
