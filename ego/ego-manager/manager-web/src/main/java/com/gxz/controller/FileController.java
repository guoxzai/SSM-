package com.gxz.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gxz.utils.FastDFSUtil;

/**
 * 文件上传
 * @author User
 *
 */
@Controller
public class FileController {


	@Value("${file.server}")
	private String fileServerPath;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public Object upLoadFile(String dir,MultipartFile uploadFile) {
		System.out.println(dir);
		System.out.println(uploadFile.getName());
		System.out.println(uploadFile.getOriginalFilename());
//		uploadFile.getBytes();
//		uploadFile.getInputStream();
		String originalFilename = uploadFile.getOriginalFilename();
		String extName = originalFilename.subSequence(originalFilename.lastIndexOf(".")+1, originalFilename.length()).toString();
		String filePath = null ;
		try {
			filePath = FastDFSUtil.uploadFile(uploadFile.getBytes(), extName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> result = new HashMap<String,Object>();
		
		if(filePath!=null) {
			result.put("error", 0);
//			g1/M00/00/00/wKjnjFyYkkOANAQNAACmsXK7IkU547.jpg
			result.put("url", fileServerPath+"/"+filePath);
		}else {
			result.put("error", 1);
			result.put("message", "图片上传失败");
		}
		return result;
	}

}
