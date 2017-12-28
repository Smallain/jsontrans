package com.wuyuhang.spark.tool

import scala.collection.mutable.{Map => MuMap}

object StructAnalysis extends WriteToFile {
	var map_j: Map[String, Any] = Map()
	
	def mapAnalysis(map: Map[String, Any]): Map[String, Any] = {
		
		for ((key, value) <- map) {
			
			//println(s"处理Json内部的Map key是:$key	value结构:$value		类型是：${value.getClass}")
			
			val value_in = value match {
				case num: Double => num //println(s"Map内部的key是： ${key+"_"} 	Value是：{$num}    数据类型是Double")
				case str: String => str //println(s"Map内部的key是： ${key+"_"} 	Value是：{$str}    数据类型是String")
				case list: List[Map[String, Any]] => {
					for (map <- list) {
						for ((k, v) <- map) {
							val tmp = Map(key + "_" + k -> v)
							mapAnalysis(tmp)
						}
					}
				} //.foreach(mapAnalysis)
				case map: Map[String, Any] => {
					for ((k, v) <- map) {
						val tmp = Map(key + "_" + k -> v)
						mapAnalysis(tmp)
					}
				} //mapAnalysis(map)
				case _ => "unknown data struct"
				//withWriter("/Users/smallain/data_tmp/its_log/fail")("its_log_failed_analysis_error")()
				
			}
			map_j += (key -> value_in)
			
			//println(s"输出结果${key -> value_in}")
		}
		map_j
		//	println(s"最终的map是$map_j")
	}
	
}
