package com.wuyuhang.spark

import JsonTrans._
import org.apache.spark.sql.SparkSession

object SparkAssembly {
	def main(args: Array[String]): Unit = {
		val spark = SparkSession
			.builder()
			.master("local[1]")
			.appName("SparkTest")
			.getOrCreate()
		
		//原始解析文件路径
		val pathSource = "/Users/smallain/data_tmp/temp2.txt"
		//原始文件解析RDD
		val sourceRDD = spark.sparkContext.textFile(pathSource)
		val num_count = sourceRDD.count()
		println(s"读入总行数：$num_count")
		sourceRDD.foreach(trans)
		//原始文件解析成功路径
		val succeedJson = "/Users/smallain/data_tmp/its_log/succ/its_log_succeed_trans.txt"
		//原始文件解析成功RDD
		val succeedJsonRDD = spark.sparkContext.textFile(succeedJson)
		val num_succ = succeedJsonRDD.count()
		println(s"读入总行数：$num_succ")
		//原始文件解析成功拆分处理方法调用
		succeedJsonRDD.foreach(transStruct)
		
		
		
		//		//dataset
		//
		//		import spark.implicits._
		//		val dataSet = spark.read.textFile(filepath)
		//			.flatMap(_.split(","))
		//			.map(x => (x, 1))
		//			.groupBy("_1")
		//			.count().toDF("NUM","VALUE")
		//
		//		dataSet.show()
	}
}
