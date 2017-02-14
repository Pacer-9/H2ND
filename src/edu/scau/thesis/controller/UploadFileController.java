package edu.scau.thesis.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/picture")
public class UploadFileController {
	@Autowired
	private Properties applicationProps; 
	@RequestMapping("/upload")
	@ResponseBody
	public Map upload(@RequestParam(value = "files", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {  
	    Map<String,Object> map = new HashMap<String,Object>();
		String fileName = new Date().getTime()+".jpg";  
	    try {
			this.savePicture(fileName, file);
		} catch (IOException e) {
			map.put("error", "图片上传错误，请重新上传");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    map.put("fileName", fileName);
	    return map;
	} 
	private void savePicture(String newFileName, MultipartFile filedata) throws IOException {
        // TODO Auto-generated method stub
        // 根据配置文件获取服务器图片存放路径
        String picDir = "";
        //这里封装了读取配置文件的方法 配置文件中有图片的存放地址和获取地址
		picDir = applicationProps.getProperty("savePicUrl");
        String saveFilePath = picDir;

        /* 构建文件目录 */
        File fileDir = new File(saveFilePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {
            FileOutputStream out = new FileOutputStream(saveFilePath + "\\"
                    + newFileName);
            // 写入文件
            out.write(filedata.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

	public Properties getApplicationProps() {
		return applicationProps;
	}
	public void setApplicationProps(Properties applicationProps) {
		this.applicationProps = applicationProps;
	}
	
}
