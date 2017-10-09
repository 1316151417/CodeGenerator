package com.swust.zj.sss.model;

import java.util.LinkedList;
import java.util.List;

import com.swust.zj.template.JavaFile;

/**
* @author 周杰
* @time 2017年10月8日 下午2:22:28
*/
public class ServiceInterfaceFile extends JavaFile {
	private String model;
	public ServiceInterfaceFile(String fileDir, String mainPackage, String curPackage, String model) {
		super(fileDir, getClassName(model).append(".java").toString(), mainPackage+"."+curPackage, INTERFACE_TYPE, getClassName(model).toString());
		this.model = model;
		List<String> imports = new LinkedList<>();
		List<String> classAnnotations = new LinkedList<>();
		List<String> extendss = new LinkedList<>();
		List<String> implementss = new LinkedList<>();
		List<JavaFile.Field> fields = new LinkedList<>();
		List<Method> methods = new LinkedList<>();
		
		//imports
		imports.add(getImportName(mainPackage, model).toString());
		imports.add("java.util.List");
		imports.add("org.springframework.data.domain.Page");
		
		//classAnnotations none
		
		//extendss none
		
		//implementss none
		
		//fields none
		
		//methods
		List<String> annotations = null;
		String modifier = "public";
		String resultType;
		String name;
		String args;
		List<String> exceptions = null;
		String methodBody = null;
		//add
		resultType = getModelClass(model).toString();
		name = "add";
		args = getModelClass(model).append(" ").append(getModelName(model)).toString();
		Method method = new Method(annotations, modifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//delete
		resultType = "void";
		name = "delete";
		args = "Integer id";
		method = new Method(annotations, modifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//update
		resultType = getModelClass(model).toString();
		name = "update";
		args = getModelClass(model).append(" ").append(getModelName(model)).toString();
		method = new Method(annotations, modifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//get
		resultType = getModelClass(model).toString();
		name = "get";
		args = "Integer id";
		method = new Method(annotations, modifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//getAll
		resultType = new StringBuilder("List<").append(getModelClass(model).toString()).append(">").toString();
		name = "getAll";
		args = "";
		method = new Method(annotations, modifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//getPage
		resultType = new StringBuilder("Page<").append(getModelClass(model).toString()).append(">").toString();
		name = "getPage";
		args = "Integer page, Integer rows";
		method = new Method(annotations, modifier, resultType, name, args, exceptions, methodBody);
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
	public static StringBuilder getClassName(String model) {
		StringBuilder className = new StringBuilder();
		className.append(getModelClass(model));
		className.append("Service");
		return className;
	}
	public static StringBuilder getImportName(String packagei,String model) {
		StringBuilder importName = new StringBuilder();
		importName.append(packagei);
		importName.append(".model.");
		importName.append(getModelClass(model));
		return importName;
	}
}
