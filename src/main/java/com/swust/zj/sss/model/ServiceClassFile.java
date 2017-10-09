package com.swust.zj.sss.model;

import java.util.LinkedList;
import java.util.List;

import com.swust.zj.template.JavaFile;

/**
* @author 周杰
* @time 2017年10月8日 下午2:57:31
*/
public class ServiceClassFile extends JavaFile {
	private String model;
	public ServiceClassFile(String fileDir, String mainPackage, String curPackage, String model) {
		super(fileDir, getClassName(model).append(".java").toString(), mainPackage+"."+curPackage, CLASS_TYPE, getClassName(model).toString());
		this.model = model;
		List<String> imports = new LinkedList<>();
		List<String> classAnnotations = new LinkedList<>();
		List<String> extendss = new LinkedList<>();
		List<String> implementss = new LinkedList<>();
		List<JavaFile.Field> fields = new LinkedList<>();
		List<Method> methods = new LinkedList<>();
		
		//imports
		imports.add(getImportName(mainPackage, model).toString());
		imports.add(new StringBuilder().append(mainPackage).append(".").append("service").append(".").append(getModelClass(model)).append("Service").toString());
		imports.add(new StringBuilder().append(mainPackage).append(".").append("repository").append(".").append(getModelClass(model)).append("Repository").toString());
		imports.add("java.util.List");
		imports.add("org.springframework.beans.factory.annotation.Autowired");
		imports.add("org.springframework.data.domain.Page");
		imports.add("org.springframework.data.domain.PageRequest");
		imports.add("org.springframework.data.domain.Pageable");
		imports.add("org.springframework.stereotype.Service");
		imports.add("org.springframework.transaction.annotation.Isolation");
		imports.add("org.springframework.transaction.annotation.Propagation");
		imports.add("org.springframework.transaction.annotation.Transactional");
		
		//classAnnotations
		classAnnotations.add("Service");
		
		//extendss none
		
		//implementss
		implementss.add(new StringBuilder().append(getModelClass(model)).append("Service").toString());
		
		//fields
		List<String> fannotations = new LinkedList<>();
		fannotations.add("Autowired");
		String fmodifier = "private";
		String ftype = new StringBuilder().append(getModelClass(model)).append("Repository").toString();
		String fname = getModelName(model).append("Repository").toString();
		String value = null;
		Field field = new Field(fannotations, fmodifier , ftype, fname, value);
		fields.add(field);
		
		//methods
		List<String> mannotations = new LinkedList<>();
		String mmodifier = "public";
		String resultType;
		String name;
		String args;
		List<String> exceptions = null;
		String methodBody = null;
		//add
		mannotations.add("Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)");
		resultType = getModelClass(model).toString();
		name = "add";
		args = getModelClass(model).append(" ").append(getModelName(model)).toString();
		methodBody = buildMethod(new String[] {
				new StringBuilder()
				.append("return ")
				.append(getModelName(model)
				.append("Repository"))
				.append(".save(")
				.append(getModelName(model))
				.append(");")
				.toString()
		});
		Method method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//delete
		mannotations = new LinkedList<>();
		mannotations.add("Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)");
		resultType = "void";
		name = "delete";
		args = "Integer id";
		methodBody = buildMethod(new String[] {
				new StringBuilder()
				.append(getModelName(model)
				.append("Repository"))
				.append(".delete(id);")
				.toString()
		});
		method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//update
		mannotations = new LinkedList<>();
		mannotations.add("Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)");
		resultType = getModelClass(model).toString();
		name = "update";
		args = getModelClass(model).append(" ").append(getModelName(model)).toString();
		methodBody = buildMethod(new String[] {
				new StringBuilder()
				.append("return ")
				.append(getModelName(model).append("Repository"))
				.append(".saveAndFlush(")
				.append(getModelName(model))
				.append(");")
				.toString()
		});
		method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//get
		mannotations = null;
		resultType = getModelClass(model).toString();
		name = "get";
		args = "Integer id";
		methodBody = buildMethod(new String[] {
				new StringBuilder()
				.append("return ")
				.append(getModelName(model).append("Repository"))
				.append(".findOne(id);")
				.toString()
		});
		method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//getAll
		resultType = new StringBuilder("List<").append(getModelClass(model).toString()).append(">").toString();
		name = "getAll";
		args = null;
		methodBody = buildMethod(new String[] {
				new StringBuilder()
				.append("return ")
				.append(getModelName(model).append("Repository"))
				.append(".findAll();")
				.toString()
		});
		method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//getPage
		resultType = new StringBuilder("Page<").append(getModelClass(model).toString()).append(">").toString();
		name = "getPage";
		args = "Integer page, Integer rows";
		methodBody = buildMethod(new String[] {
				new StringBuilder()
				.append("Pageable pageable = new PageRequest(page-1, rows);")
				.toString(),
				new StringBuilder()
				.append("return ")
				.append(getModelName(model).append("Repository"))
				.append(".findAll(pageable);")
				.toString()
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
	public static StringBuilder getClassName(String model) {
		StringBuilder className = new StringBuilder();
		className.append(getModelClass(model));
		className.append("ServiceImpl");
		return className;
	}
	public static StringBuilder getImportName(String packagei,String model) {
		StringBuilder importName = new StringBuilder();
		importName.append(packagei);
		importName.append(".model.");
		importName.append(getModelClass(model));
		return importName;
	}
	public static StringBuilder getImportName(String packagei,String and,String name) {
		StringBuilder importName = new StringBuilder();
		importName.append(packagei);
		importName.append(".");
		importName.append(and);
		importName.append(".");
		importName.append(name);
		return importName;
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
