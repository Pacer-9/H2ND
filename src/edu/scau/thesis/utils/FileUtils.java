package edu.scau.thesis.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class FileUtils {
	@Autowired
	private Properties applicationProps;
	public void saveFile(String newFileName, MultipartFile filedata) throws IOException {
        // TODO Auto-generated method stub
        //文件目录
        String saveDir = applicationProps.getProperty("savePicUrl");
        System.out.println();
        //存放路径
        String saveFilePath = saveDir+newFileName;
        /* 构建文件目录 */
        File fileDir = new File(saveDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        
        try {
            FileOutputStream out = new FileOutputStream(saveFilePath);
            // 写入文件
            out.write(filedata.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return saveFilePath;
    }
	public Properties getApplicationProps() {
		return applicationProps;
	}
	public void setApplicationProps(Properties applicationProps) {
		this.applicationProps = applicationProps;
	}
	

}
