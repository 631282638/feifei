package com.shuixiaofei.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class PicZoom {
	/**
	 * filename:源文件名 thumbWidth:目标宽度 thumbHeight:目标高度 quality:画质 0-100 outFilename:目标文件名 string
	 */
	public static String picZoom(String filename, int thumbWidth, int thumbHeight, int quality, String outFilename) {

		try {
			// load image from
			Image iag = ImageIO.read(new File(filename));
			// WIDTH and HEIGHT
			double thumbRatio = (double) thumbWidth / (double) thumbHeight;
			int imageWidth = iag.getWidth(null);
			int imageHeight = iag.getHeight(null);
			double imageRatio = (double) imageWidth / (double) imageHeight;

			if (thumbRatio < imageRatio) {
				thumbHeight = (int) (thumbWidth / imageRatio);
			} else {
				thumbWidth = (int) (thumbHeight * imageRatio);
			}
			// draw original image to thumbnail
			BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = thumbImage.createGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			graphics2D.drawImage(iag.getScaledInstance(thumbWidth, thumbHeight, Image.SCALE_SMOOTH), 0, 0, thumbWidth, thumbHeight, null);
			// save thumbnail image to outFilename
			
			/*BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFilename));
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);

			quality = Math.max(0, Math.min(quality, 100));
			param.setQuality((float) quality / 100.0f, false);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(thumbImage);*/
			
			//	FileOutputStream out = new FileOutputStream(topath);
			//	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			//	JPEGEncodeParam param = encoder
			//			.getDefaultJPEGEncodeParam(mBufferedImage);
				FileOutputStream out = new FileOutputStream(outFilename);  
				ImageIO.write(thumbImage, outFilename.substring(outFilename
						.lastIndexOf(".") + 1), out);	
			//	param.setQuality(quality, true);// 默认0.75
			//	encoder.setJPEGEncodeParam(param);
			//	encoder.encode(mBufferedImage);
			
			out.close();
			System.out.println("压缩完成");
			return thumbWidth + "," + thumbHeight;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * data:数据库得到的图片的字节数据 thumbWidth:目标宽度 thumbHeight:目标高度 quality:画质 0-100 outFilename:目标文件名 string
	 */
	public static String picZoom(byte[] data, int thumbWidth, int thumbHeight, int quality, String outFilename) {

		try {
			// load image from
			Image iag = ImageIO.read(new ByteArrayInputStream(data));
			// WIDTH and HEIGHT
			double thumbRatio = (double) thumbWidth / (double) thumbHeight;
			int imageWidth = iag.getWidth(null);
			int imageHeight = iag.getHeight(null);
			double imageRatio = (double) imageWidth / (double) imageHeight;

			if (thumbRatio < imageRatio) {
				thumbHeight = (int) (thumbWidth / imageRatio);
			} else {
				thumbWidth = (int) (thumbHeight * imageRatio);
			}
			// draw original image to thumbnail
			BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = thumbImage.createGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			graphics2D.drawImage(iag.getScaledInstance(thumbWidth, thumbHeight, Image.SCALE_SMOOTH), 0, 0, thumbWidth, thumbHeight, null);
			// save thumbnail image to outFilename
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFilename));
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);

			quality = Math.max(0, Math.min(quality, 100));
			param.setQuality((float) quality / 100.0f, false);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(thumbImage);
			out.close();
			System.out.println("压缩完成");
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 * @throws Exception
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			String separatorStr = "\\";
			if(newPath.lastIndexOf("/") > 0){
				separatorStr = "/";
			}else{
				separatorStr = File.separator;
			}
				System.out.println("复制单个文件分隔separatorStr=" + separatorStr);
			
			String newPathFolder = newPath.substring(0, newPath.lastIndexOf(separatorStr));
			File folder = new File(newPathFolder);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath); 
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				fs.flush();
				inStream.close();
				fs.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// PicZoom.picZoom("D:\\shigongtouxiang.jpg", 400, 400, 70, "D:\\shigongtouxiang_1.jpg");
		try {

			copyFile("E:/logo.png", "E:/a/2.png");

			// BufferedInputStream in = new BufferedInputStream(new FileInputStream("E:/2.png"));
			// ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
			//
			// byte[] temp = new byte[1024];
			// int size = 0;
			// while ((size = in.read(temp)) != -1) {
			// out.write(temp, 0, size);
			// }
			// in.close();
			// byte[] content = out.toByteArray();
			// System.out.println(PicZoom.picZoom(content, 400, 400, 70, "b:\\temp.png"));
			// System.out.println("111");
			/*
			 * File file = new File("b:\\temp.png"); if(file.exists()){ file.delete(); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
