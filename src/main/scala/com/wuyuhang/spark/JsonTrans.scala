package com.wuyuhang.spark

import com.wuyuhang.spark.tool.WriteToFile
import com.wuyuhang.spark.tool.StructAnalysis._

import scala.util.parsing.json.JSON
import scala.util.parsing.json.JSONObject


object JsonTrans extends WriteToFile {
	var count_Lines = 0
	var keylist: List[String] = Nil
	
	def trans(input: String) {
		val matched = JSON.parseFull(input)
		matched match {
			
			//匹配Json成功
			case Some(list: Map[String, Any]) =>
				//println(list)
			{
				//List[Map[String, Any]]]
				//val tmpls = list("data").head("payload")
				scalaWriteAppendLine("/Users/smallain/data_tmp/its_log/succ")("its_log_succeed_trans.txt")(input)
				//println(tmpls.getClass)
			}
			
			//匹配Json失败
			case None => scalaWriteAppendLine("/Users/smallain/data_tmp/its_log/fail")("its_log_failed_trans.txt")(input)
			
			//其他情况
			case other => println("Unknown data structure: " + other)
			
		}
	}
	
	
	def transStruct(input: String) {
		
		
		val matched = JSON.parseFull(input)
		matched match {
			
			//匹配Json成功
			case Some(list: Map[String, Any]) => {
				val mapped = mapAnalysis(list)
				val mapfilter = mapped.filter(_._2.toString != "()")
				
				
				val map_sorted = mapfilter.toList.sortBy(_._1).toMap
				
				
				val maySize = map_sorted.keySet.size
				
				if (maySize > count_Lines) {
					count_Lines = maySize
					keylist = map_sorted.keySet.toList
				}
				
				val tmp = JSONObject(map_sorted)
				
				scalaWriteAppendLine("/Users/smallain/data_tmp/its_log/succ")("its_log_transed_sorted_json.txt")(tmp.toString())
				scalaWriteOverWrite("/Users/smallain/data_tmp/its_log/succ")("its_log_keySet.txt")(keylist.toString())
				
			}
			
			//匹配Json失败
			case None => scalaWriteAppendLine("/Users/smallain/data_tmp/its_log/fail")("its_log_failed_trans2.txt")(input)
			
			
			
			//其他情况
			case other => println("Unknown data structure: " + other)
			
			
		}
		
	}
	
}