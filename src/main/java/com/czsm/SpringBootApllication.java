package com.czsm;

import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mac(刘平) 20180728
 *
 */
@SpringBootApplication
public class SpringBootApllication {
	private static Logger logger = Logger.getLogger(SpringBootApllication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApllication.class, args);
		logger.info("\n项目已经启动成功  请开始使用。。。。。。。。。。。\n");
	}

	 

}
