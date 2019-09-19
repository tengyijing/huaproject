package cn.qst.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.huacao.common.utils.JsonUtils;
import common.fastdfs.FastDFSClient;


/**
 * 文件上传处理
 * @author Administrator
 *
 */
@Controller
public class PictureController {
	
	//上传图片的url地址
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uplodFile(@RequestParam(name="uploadFile")  MultipartFile uplodFile) {
		//上传文件
		try {
			//通过图片服务器的配置文件初始化文件上传客户端
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:fastdfs/client.conf");
			//获取文件名
			String originalFilename = uplodFile.getOriginalFilename();
			//截取后缀
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			//上传文件 返回一个图片路径(不带服务器的ip地址)
			String path = fastDFSClient.uploadFile(uplodFile.getBytes(), extName);
			//拼接图片服务器的url(将ip地址带上得到一个完整的网络路径)
			String url = IMAGE_SERVER_URL + path;
			Map result = new HashMap();
			result.put("error", 0);
			result.put("url", url);
			return JsonUtils.objectToJson(result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map result = new HashMap();
			result.put("error", 1);
			result.put("massege", "上传失败");
			return JsonUtils.objectToJson(result);
		}
	}	
}
