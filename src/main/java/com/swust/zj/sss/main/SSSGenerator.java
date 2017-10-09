package com.swust.zj.sss.main;

import java.util.Scanner;

import com.swust.zj.sss.model.ApplicationClassFile;
import com.swust.zj.sss.model.ApplicationYmlResourceFile;
import com.swust.zj.sss.model.ControllerClassFile;
import com.swust.zj.sss.model.PojoClassFile;
import com.swust.zj.sss.model.PomXmlResourceFile;
import com.swust.zj.sss.model.RepositoryInterfaceFile;
import com.swust.zj.sss.model.ResultClassFile;
import com.swust.zj.sss.model.ServiceClassFile;
import com.swust.zj.sss.model.ServiceInterfaceFile;

/**
* @author 周杰
* @time 2017年10月9日 下午12:07:26
*/
public class SSSGenerator {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.print("BaseDir:");
		String baseDir = scanner.next();
		System.out.print("ProjectName:");
		String projectName = scanner.next();
		String resourcesDir = "src/main/resources";
		String javaDir = "src/main/java";
		System.out.print("PackageDir:");
		String packageDir = scanner.next();
		
		PomXmlResourceFile pxrf = new PomXmlResourceFile(baseDir+"/"+projectName);
		pxrf.generate();
		
		ApplicationYmlResourceFile ayrf = new ApplicationYmlResourceFile(baseDir+"/"+projectName+"/"+resourcesDir);
		ayrf.generate();
		
		ApplicationClassFile acf = new ApplicationClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir, toPackage(packageDir));
		acf.generate();
		
		ResultClassFile rcf = new ResultClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir+"/util", toPackage(packageDir), "util");
		rcf.personalGenerate();
		String model;
		while(true) {
			System.out.print("PojoName(input \"exit\" to exit):");
			model = scanner.next();
			if(model.equals("exit")) {
				break;
			}
			PojoClassFile pcf = new PojoClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir+"/model", toPackage(packageDir), "model", model);
			pcf.generate();
			RepositoryInterfaceFile rif = new RepositoryInterfaceFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir+"/repository",toPackage(packageDir),"repository",model);
			rif.generate();
			ServiceInterfaceFile sif = new ServiceInterfaceFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir+"/service",toPackage(packageDir),"service",model);
			sif.generate();
			ServiceClassFile scf = new ServiceClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir+"/service/impl",toPackage(packageDir),"service.impl",model);
			scf.generate();
			ControllerClassFile ccf = new ControllerClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir+"/controller", toPackage(packageDir),"controller", model);
			ccf.generate();
		}
	}
	private static String toPackage(String packageDir) {
		return packageDir.replaceAll("/", ".");
	}
}
