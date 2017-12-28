package com.swust.zj.template;

import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Iterator;

/**
* @author 周杰
* @time 2017年9月27日 上午11:52:46
*/
public class JavaFile {
	protected static final String CLASS_TYPE = "class";
	protected static final String INTERFACE_TYPE = "interface";
	protected String fileDir;
	protected String fileName;
	protected String packagei;
	protected String type;
	protected String name;
	protected Collection<String> imports;
	protected Collection<String> classAnnotations;
	protected Collection<String> extendss;
	protected Collection<String> implementss;
	protected Collection<Field> fields;
	protected Collection<Method> methods;
	
	protected String resource;
	
	protected static class Field{
    	Collection<String> annotations;
        String modifier;
        String type;
        String name;
        String value;
        public Field(Collection<String> annotations, String modifier, String type, String name, String value){
            this.annotations = annotations;
            this.modifier = modifier;
            this.type = type;
            this.name = name;
            this.value = value;
        }
    }
	protected static class Method{
    	Collection<String> annotations;
        String modifier;
        String resultType;
        String name;
        String args;
        Collection<String> exceptions;
        String methodBody;
        public Method(Collection<String> annotations, String modifier, String resultType, String name, String args, Collection<String> exceptions, String methodBody){
            this.annotations = annotations;
            this.modifier = modifier;
            this.resultType = resultType;
            this.name = name;
            this.args = args;
            this.exceptions = exceptions;
            this.methodBody = methodBody;
        }
    }
    public JavaFile(String fileDir, String fileName, String packagei, String type, String name) {
		super();
		if(!type.equals("class") && !type.equals("interface")) {
			throw new IllegalArgumentException();
		}
		this.fileDir = fileDir;
		this.fileName = fileName;
		this.packagei = packagei;
		this.type = type;
		this.name = name;
	}
    protected void init(String resource) {
    	this.resource = resource;
    }
    protected void init(Collection<String> imports,
    		Collection<String> classAnnotations,
			Collection<String> extendss,
    		Collection<String> implementss,
    		Collection<Field> fields,
    		Collection<Method> methods) {
    	this.imports = imports;
    	this.classAnnotations = classAnnotations;
    	this.extendss = extendss;
    	this.implementss = implementss;
    	this.fields = fields;
    	this.methods = methods;
    }
    protected File makeJavaDir() throws Exception{
        synchronized (JavaFile.class) {
            File dir = new File(fileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return dir;
        }
    }
    protected File makeJavaFile() throws Exception{
        synchronized (JavaFile.class) {
            File dir = makeJavaDir();
            if (!fileName.endsWith(".java")) {
                fileName += ".java";
            }
            File file = new File(dir, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        }
    }
    public void generate() throws Exception{
        //startWrite
        File file = makeJavaFile();
        FileWriter fileWriter = new FileWriter(file);
        //initString
        StringBuilder stringBuilder = new StringBuilder();
        //ensureWritePackage
        stringBuilder.append("package ");
        stringBuilder.append(packagei);
        stringBuilder.append(";\n");
        //ensureWriteImport
        if(imports != null) {
            Iterator<String> iterator = imports.iterator();
            while (iterator.hasNext()) {
                stringBuilder.append("import ");
                stringBuilder.append(iterator.next());
                stringBuilder.append(";\n");
            }
        }
        //writeClassAnnotations
        if(classAnnotations != null){
            Iterator<String> iterator = classAnnotations.iterator();
            while(iterator.hasNext()){
            	stringBuilder.append("@");
                stringBuilder.append(iterator.next());
                stringBuilder.append("\n");
            }
        }
        //ensureWriteClassStart
        stringBuilder.append("public ");
        stringBuilder.append(type);
        stringBuilder.append(" ");
        stringBuilder.append(name);
        stringBuilder.append(" ");
        //ensureWriteExtends
        if(extendss != null) {
	        Iterator<String> iterator = extendss.iterator();
	        int count = 0;
	        while(iterator.hasNext()) {
	        	if(count==0) {
	        		stringBuilder.append("extends ");
	        	}else {
	        		stringBuilder.append(",");
	        	}
	        	stringBuilder.append(iterator.next());
	        	count++;
	        }
	        if(count > 0) {
	        	stringBuilder.append(" ");
	        }
        }
        //ensureWritesImplements
        if(implementss != null) {
	        Iterator<String> iterator = implementss.iterator();
	        int count = 0;
	        while(iterator.hasNext()) {
	        	if(count==0) {
	        		stringBuilder.append("implements ");
	        	}else {
	        		stringBuilder.append(",");
	        	}
	        	stringBuilder.append(iterator.next());
	        	count++;
	        }
	        if(count > 0) {
	        	stringBuilder.append(" ");
	        }
        }
        stringBuilder.append("{\n");
        //ensureWriteField
        if(fields != null) {
            Iterator<Field> iterator = fields.iterator();
            while (iterator.hasNext()) {
                Field field = iterator.next();
                if(field.annotations!=null){
                    Iterator<String> iteratorAnnotations = field.annotations.iterator();
                    while(iteratorAnnotations.hasNext()) {
                        stringBuilder.append("\t");
                        stringBuilder.append("@");
                        stringBuilder.append(iteratorAnnotations.next());
                        stringBuilder.append("\n");
                    }
                }
                if(field.modifier!=null){
                    stringBuilder.append("\t");
                    stringBuilder.append(field.modifier);
                    stringBuilder.append(" ");
                }
                if(field.type!=null){
                    stringBuilder.append(field.type);
                    stringBuilder.append(" ");
                }
                if(field.name!=null){
                    stringBuilder.append(field.name);
                }
                if(field.value!=null){
                    stringBuilder.append(" = ");
                    stringBuilder.append(field.value);
                }
                stringBuilder.append(";\n");
            }
        }
        //ensureWriteMethod
        if(type.equals("class")) {
	        if(methods != null) {
	            Iterator<Method> iterator = methods.iterator();
	            while (iterator.hasNext()) {
	                Method method = iterator.next();
	                if(method.annotations!=null){
	                    Iterator<String> iteratorAnnotations = method.annotations.iterator();
	                    while(iteratorAnnotations.hasNext()) {
	                        stringBuilder.append("\t");
	                        stringBuilder.append("@");
	                        stringBuilder.append(iteratorAnnotations.next());
	                        stringBuilder.append("\n");
	                    }
	                }
	                if(method.modifier!=null){
	                    stringBuilder.append("\t");
	                    stringBuilder.append(method.modifier);
	                    stringBuilder.append(" ");
	                }
	                if(method.resultType!=null){
	                    stringBuilder.append(method.resultType);
	                    stringBuilder.append(" ");
	                }
	                if(method.name!=null){
	                    stringBuilder.append(method.name);
	                }
	                stringBuilder.append("(");
	                if(method.args!=null){
	                    stringBuilder.append(method.args);
	                }
	                stringBuilder.append(")");
	                if(method.exceptions!=null){
	                    Iterator<String> iteratorExceptions = method.exceptions.iterator();
	                    int count = 0;
	                    while(iteratorExceptions.hasNext()) {
	                        if(count == 0){
	                            stringBuilder.append(" throws ");
	                        }else{
	                            stringBuilder.append(",");
	                        }
	                        stringBuilder.append(iteratorExceptions.next());
	                        count++;
	                    }
	                }
	                stringBuilder.append("{\n");
	                if(method.methodBody!=null){
	                    stringBuilder.append(method.methodBody);
	                }
	                stringBuilder.append("\t}\n");
	            }
	        }
        }else if(type.equals("interface")) {
	        if(methods != null) {
	            Iterator<Method> iterator = methods.iterator();
	            while (iterator.hasNext()) {
	                Method method = iterator.next();
	                if(method.modifier!=null){
	                    stringBuilder.append("\t");
	                    stringBuilder.append(method.modifier);
	                    stringBuilder.append(" ");
	                }
	                if(method.resultType!=null){
	                    stringBuilder.append(method.resultType);
	                    stringBuilder.append(" ");
	                }
	                if(method.name!=null){
	                    stringBuilder.append(method.name);
	                    stringBuilder.append("(");
	                }
	                if(method.args!=null){
	                    stringBuilder.append(method.args);
	                    stringBuilder.append(")");
	                }
	                if(method.exceptions!=null){
	                    Iterator<String> iteratorExceptions = method.exceptions.iterator();
	                    int count = 0;
	                    while(iteratorExceptions.hasNext()) {
	                        if(count == 0){
	                            stringBuilder.append(" throws ");
	                        }else{
	                            stringBuilder.append(",");
	                        }
	                        stringBuilder.append(iteratorExceptions.next());
	                        count++;
	                    }
	                }
	                stringBuilder.append(";\n");
	            }
	        }
        }
        //ensureWriteClassEnd
        stringBuilder.append("}");
        //writeAll
        fileWriter.write(stringBuilder.toString());
        //endWrite
        fileWriter.flush();
        fileWriter.close();
    }
    public void personalGenerate() throws Exception {
    	//startWrite
        File file = makeJavaFile();
        FileWriter fileWriter = new FileWriter(file);
        //initString
        StringBuilder stringBuilder = new StringBuilder();
        //ensureWritePackage
        stringBuilder.append("package ");
        stringBuilder.append(packagei);
        stringBuilder.append(";\n");
        //addResource
        if(resource != null) {
        	stringBuilder.append(resource);
        }
        //writeAll
        fileWriter.write(stringBuilder.toString());
        //endWrite
        fileWriter.flush();
        fileWriter.close();
    }
}
