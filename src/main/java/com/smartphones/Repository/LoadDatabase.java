//package com.lab1gabarsolon.Repository;
//
//import com.lab1gabarsolon.Model.Display;
//import com.lab1gabarsolon.Model.Smartphone;
//import com.lab1gabarsolon.Model.Transaction;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//
//@Configuration
//class LoadDatabase{
//	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//	@Bean
//	CommandLineRunner initDatabase(SmartphoneRepository smartphoneRepository, DisplayRepository displayRepository,
//								   CustomerRepository customerRepository, TransactionRepository transactionRepository){
//		return args->{log.info("Database initialized successfully");};
//	}
//}