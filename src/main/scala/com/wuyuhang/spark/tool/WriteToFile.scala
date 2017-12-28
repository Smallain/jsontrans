package com.wuyuhang.spark.tool


import java.io.Writer
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.{File => JFile}

import better.files.Dsl.mkdirs
import better.files._

/**
	* Created by smallain on 2017/9/3.
	*/
trait WriteToFile {
	
	/**
		* path:			输出文件路径
		* fileName:	输出文件名称
		* messages:	输出文件内容
		*/
	type Path = String
	
	def withWriter(path: Path)(fileName: String)(messages: String): Unit = {
		var writer: Writer = null
		try {
			val fileDir = new JFile(path)
			if (fileDir.isDirectory) {
				val file = new JFile(path + "/" + fileName)
				if (!file.exists()) file.createNewFile()
				writer = new BufferedWriter(new FileWriter(file, true))
				writer.write(messages + "\r\n")
				writer.flush()
				writer.close()
			} else {
				fileDir.mkdirs()
				val file = new JFile(path + "/" + fileName)
				if (!file.exists()) file.createNewFile()
				writer = new BufferedWriter(new FileWriter(file))
				writer.write(messages)
				writer.write("\r\n");
				writer.flush()
				writer.close()
			}
			
		} finally {
			if (writer != null) writer.close()
		}
	}
	
	/**
		* java io 处理，删除内容添加逻辑
		*
		* @param path     :输出文件路径
		* @param fileName :输出文件名称
		* @param messages :输出文件内容
		*/
	def withWriterDel(path: Path)(fileName: String)(messages: String): Unit = {
		var writer: Writer = null
		try {
			val fileDir = new JFile(path)
			if (fileDir.isDirectory) {
				val file = new JFile(path + "/" + fileName)
				if (!file.exists()) file.createNewFile()
				writer = new BufferedWriter(new FileWriter(file))
				writer.write(messages + "\r\n")
				writer.flush()
			} else {
				fileDir.mkdirs()
				val file = new JFile(path + "/" + fileName)
				if (!file.exists()) file.createNewFile()
				writer = new BufferedWriter(new FileWriter(file))
				writer.write(messages)
				writer.write("\r\n");
				writer.flush()
			}
			
		} finally {
			if (writer != null) writer.close()
		}
	}
	
	/**
		* scala io 文件写入
		* appendLine:文本末尾追加行内容
		*
		* @param path     文件夹路径
		* @param fileName 文件名称
		* @param messages 文件内容
		*/
	def scalaWriteAppendLine(path: Path)(fileName: String)(messages: String): Unit = {
		val fileDir = File(path)
		if (fileDir.isDirectory) {
			//文件路径存在
			val file = fileDir / fileName
			file.appendLine(messages)
		} else {
			//文件路径不存在
			mkdirs(fileDir)
			val file = fileDir / fileName
			file.appendLine(messages)
		}
	}
	
	/**
		* scala io 文件写入
		* append:文本末尾追加内容
		*
		* @param path     文件夹路径
		* @param fileName 文件名称
		* @param messages 文件内容
		*/
	def scalaWriteAppend(path: Path)(fileName: String)(messages: String): Unit = {
		val fileDir = File(path)
		if (fileDir.isDirectory) {
			//文件路径存在
			val file = fileDir / fileName
			file.append(messages)
		} else {
			//文件路径不存在
			mkdirs(fileDir)
			val file = fileDir / fileName
			file.append(messages)
		}
	}
	
	/**
		* scala io 文件写入
		* overwrite:覆盖重写文件
		*
		* @param path     文件夹路径
		* @param fileName 文件名称
		* @param messages 文件内容
		*/
	def scalaWriteOverWrite(path: Path)(fileName: String)(messages: String): Unit = {
		val fileDir = File(path)
		if (fileDir.isDirectory) {
			//文件路径存在
			val file = fileDir / fileName
			file.overwrite(messages)
		} else {
			//文件路径不存在
			mkdirs(fileDir)
			val file = fileDir / fileName
			file.overwrite(messages)
		}
	}
}
