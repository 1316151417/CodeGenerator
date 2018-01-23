package com.swust.zj.template.common;

import com.swust.zj.base.JavaFile;

/**
* @author 周杰
* @time 2017年10月9日 上午10:46:57
*/
public class ResultClassFile extends JavaFile {
	private static final String CLASS_NAME = "Result";
	private static final String FILE_NAME = "Result.java";
	public ResultClassFile(String fileDir, String mainPackage, String curPackage) {
		super(fileDir, FILE_NAME, mainPackage+ "."+curPackage, CLASS_TYPE, CLASS_NAME);
		
		String resource = 
				"import com.fasterxml.jackson.databind.JsonNode;\r\n" + 
				"import com.fasterxml.jackson.databind.ObjectMapper;\r\n" + 
				"import java.util.List;\r\n" + 
				"\r\n" + 
				"/**\r\n" + 
				" * Created by Zhou Jie on 2017/5/24.\r\n" + 
				" */\r\n" + 
				"public class Result {\r\n" + 
				"    private static final Integer SUCCESS_STATUS = 200;\r\n" + 
				"    private static final Integer FAILURE_STATUS = 400;\r\n" + 
				"    private static final Integer INTERNAL_ERROR_STATUS = 500;\r\n" + 
				"    private static final String SUCCESS_MESSAGE = \"success\";\r\n" + 
				"    private static final String FAILURE_MESSAGE = \"failure\";\r\n" + 
				"    private static final String INTERNAL_ERROR_MESSAGE = \"internal error\";\r\n" + 
				"    //状态\r\n" + 
				"    private Integer status;\r\n" + 
				"\r\n" + 
				"    //消息\r\n" + 
				"    private String message;\r\n" + 
				"\r\n" + 
				"    //数据\r\n" + 
				"    private Object data;\r\n" + 
				"\r\n" + 
				"    //构造方法\r\n" + 
				"    private Result(Integer status, String message, Object data) {\r\n" + 
				"        this.status = status;\r\n" + 
				"        this.message = message;\r\n" + 
				"        this.data = data;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    //无数据的成功结果\r\n" + 
				"    public static Result success(){\r\n" + 
				"        return new Result(SUCCESS_STATUS,SUCCESS_MESSAGE,null);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    //有数据的成功结果\r\n" + 
				"    public static Result success(Object data){\r\n" + 
				"        return new Result(SUCCESS_STATUS,SUCCESS_MESSAGE,data);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    //无消息的失败结果\r\n" + 
				"    public static Result failure(){\r\n" + 
				"        return new Result(FAILURE_STATUS,FAILURE_MESSAGE,null);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    //有消息的失败结果\r\n" + 
				"    public static Result failure(String message){\r\n" + 
				"        return new Result(FAILURE_STATUS,message,null);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    //无消息的内部异常结果\r\n" + 
				"    public static Result internalError(){\r\n" + 
				"        return new Result(INTERNAL_ERROR_STATUS,INTERNAL_ERROR_MESSAGE,null);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    //有消息的内部异常结果\r\n" + 
				"    public static Result internalError(String message){\r\n" + 
				"        return new Result(INTERNAL_ERROR_STATUS,message,null);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    //自定义构建结果\r\n" + 
				"    public static Result build(Integer status,String message,Object data){\r\n" + 
				"        return new Result(status,message,data);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    //设置和获取成员变量的方法\r\n" + 
				"    public Integer getStatus() {\r\n" + 
				"        return status;\r\n" + 
				"    }\r\n" + 
				"    public void setStatus(Integer status) {\r\n" + 
				"        this.status = status;\r\n" + 
				"    }\r\n" + 
				"    public String getMessage() {\r\n" + 
				"        return message;\r\n" + 
				"    }\r\n" + 
				"    public void setMessage(String message) {\r\n" + 
				"        this.message = message;\r\n" + 
				"    }\r\n" + 
				"    public Object getData() {\r\n" + 
				"        return data;\r\n" + 
				"    }\r\n" + 
				"    public void setData(Object data) {\r\n" + 
				"        this.data = data;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    //json与数据转化核心类\r\n" + 
				"    private static final ObjectMapper MAPPER = new ObjectMapper();\r\n" + 
				"\r\n" + 
				"    //没有数据的转化\r\n" + 
				"    public static Result format(String json) {\r\n" + 
				"        try {\r\n" + 
				"            return MAPPER.readValue(json, Result.class);\r\n" + 
				"        } catch (Exception e) {\r\n" + 
				"            e.printStackTrace();\r\n" + 
				"        }\r\n" + 
				"        return null;\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    //数据是对象的转化\r\n" + 
				"    public static Result formatToPojo(String json, Class<?> clazz) {\r\n" + 
				"        try {\r\n" + 
				"            if (clazz == null) {\r\n" + 
				"                return MAPPER.readValue(json, Result.class);\r\n" + 
				"            }\r\n" + 
				"            JsonNode jsonNode = MAPPER.readTree(json);\r\n" + 
				"            JsonNode data = jsonNode.get(\"data\");\r\n" + 
				"            Object obj = null;\r\n" + 
				"            if (clazz != null) {\r\n" + 
				"                if (data.isObject()) {\r\n" + 
				"                    obj = MAPPER.readValue(data.traverse(), clazz);\r\n" + 
				"                } else if (data.isTextual()) {\r\n" + 
				"                    obj = MAPPER.readValue(data.asText(), clazz);\r\n" + 
				"                }\r\n" + 
				"            }\r\n" + 
				"            return build(jsonNode.get(\"status\").intValue(), jsonNode.get(\"message\").asText(), obj);\r\n" + 
				"        } catch (Exception e) {\r\n" + 
				"            e.printStackTrace();\r\n" + 
				"        }\r\n" + 
				"        return null;\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    //数据是集合的转化\r\n" + 
				"    public static Result formatToList(String jsonData, Class<?> clazz) {\r\n" + 
				"        try {\r\n" + 
				"            JsonNode jsonNode = MAPPER.readTree(jsonData);\r\n" + 
				"            JsonNode data = jsonNode.get(\"data\");\r\n" + 
				"            Object obj = null;\r\n" + 
				"            if (data.isArray() && data.size() > 0) {\r\n" + 
				"                obj = MAPPER.readValue(data.traverse(),\r\n" + 
				"                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));\r\n" + 
				"            }\r\n" + 
				"            return build(jsonNode.get(\"status\").intValue(), jsonNode.get(\"message\").asText(), obj);\r\n" + 
				"        } catch (Exception e) {\r\n" + 
				"            e.printStackTrace();\r\n" + 
				"        }\r\n" + 
				"        return null;\r\n" + 
				"    }\r\n" + 
				"}";
		super.init(resource);
	}
}
