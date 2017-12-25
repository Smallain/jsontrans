package com.wuyuhang.spark.tool


import java.io.File
import java.io.Writer
import java.io.BufferedWriter
import java.io.FileWriter


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
			val fileDir = new File(path)
			if (fileDir.isDirectory) {
				val file = new File(path + "/" + fileName)
				if (!file.exists()) file.createNewFile()
				writer = new BufferedWriter(new FileWriter(file, true))
				writer.write(messages + "\r\n")
				writer.flush()
				writer.close()
			} else {
				fileDir.mkdirs()
				val file = new File(path + "/" + fileName)
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
	
	def withWriterDel(path: Path)(fileName: String)(messages: String): Unit = {
		var writer: Writer = null
		try {
			val fileDir = new File(path)
			if (fileDir.isDirectory) {
				val file = new File(path + "/" + fileName)
				if (!file.exists()) file.createNewFile()
				writer = new BufferedWriter(new FileWriter(file))
				writer.write(messages + "\r\n")
				writer.flush()
			} else {
				fileDir.mkdirs()
				val file = new File(path + "/" + fileName)
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
	
	
}
