package com.swust.zj.template;

import java.io.File;
import java.io.FileWriter;

/**
* @author 周杰
* @time 2017年10月9日 上午11:41:54
*/
public class ResourceFile {
	protected String fileDir;
	protected String fileName;
	protected String resource;
	public ResourceFile(String fileDir, String fileName) {
		super();
		this.fileDir = fileDir;
		this.fileName = fileName;
	}
	protected void init(String resource) {
		this.resource = resource;
	}
	protected File makeResourceDir() throws Exception{
        File dir = new File(fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }
    protected File makeResourceFile() throws Exception{
    	File dir = makeResourceDir();
        File file = new File(dir, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
    public void generate() throws Exception{
        //startWrite
        File file = makeResourceFile();
        FileWriter fileWriter = new FileWriter(file);
        //initString
        StringBuilder stringBuilder = new StringBuilder();
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
