package com.gxz.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * 文件上传工具类
 * 
 * @author User
 *
 */
public class FastDFSUtil {

	private static StorageClient client = null;
	static {
		try {
			/**
			 * 使用快照版本
			 */
			ClientGlobal.init("fastdfs.conf");
			// 连接tracker
			TrackerClient trackerClient = new TrackerClient();
			TrackerServer trackerServer = trackerClient.getConnection();
			// 通过tracker 得到一storageclient
			StorageServer storageServer = null;
			client = new StorageClient(trackerServer, storageServer);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		String uploadFile = uploadFile("d://Test//2.jpg");
		System.out.println(uploadFile);
	}

	/**
	 * 文件上传
	 */
	public static String uploadFile(String localFileName) {
		// d://1111.jpg
		String extName = localFileName.subSequence(localFileName.lastIndexOf(".") + 1, localFileName.length())
				.toString();
		String fileRealPath = null;
		String[] filePath = null;
		try {
			filePath = client.upload_file(localFileName, extName, null);
		} catch (Exception e) {
			e.printStackTrace();
		} // 最后一个是文件的头信息，eg:文件的作者 文件的修改日志
		
		//   g1   /M00/00/00/wKh-hVyZiFaAa2WnAACgWbf5Vhk758.jpg
		if (filePath != null && filePath.length == 2) {
			fileRealPath = filePath[0] + "/" + filePath[1];
		}
		return fileRealPath;
	}

	public static String uploadFile(byte[] bs, String extName) {
		String uploadFilePath = null;
		String[] upload_file = null;
		try {
			upload_file = client.upload_file(bs, extName, null);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
		if (upload_file != null && upload_file.length == 2) {
			uploadFilePath = upload_file[0] + "/" + upload_file[1];
		}
		return uploadFilePath;
	}

}
