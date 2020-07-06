package net.oopscraft.study.influxdb;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.LogLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.QueryResult.Result;

public class InfluxDbTest {
	
	
	public static void main(String[] args) throws Exception {
		executeInsertQuery();
		executeSelectQuery();
	}
	
	/**
	 * return test connection
	 * @return
	 */
	private static InfluxDB getConnection() {
		String databaseUrl = "http://127.0.0.1:8086";
		String username = "test1";
		String password = "xptmxm1";
		InfluxDB influxDb = InfluxDBFactory.connect(databaseUrl, username, password);
		influxDb.setLogLevel(LogLevel.FULL);
		influxDb.setDatabase("test");
		return influxDb;
	}
	
	/**
	 * executes insert query
	 * @throws Exception
	 */
	public static void executeInsertQuery() throws Exception {
		InfluxDB influxDb = getConnection();
		try {
			Point point = Point.measurement("memory")
					.time(System.currentTimeMillis(), TimeUnit.MICROSECONDS)
					.addField("host", "server1")
					.addField("free", 10020)
					.addField("used", 20001)
					.build();
			influxDb.write(point);
		}catch(Exception e) {
			throw e;
		}finally {
			influxDb.close();
		}
	}
	
	/**
	 * executes select query
	 * @throws Exception
	 */
	public static void executeSelectQuery() throws Exception {
		InfluxDB influxDb = getConnection();
		try {
			Query query = new Query("select * from memory");
			QueryResult queryResult = influxDb.query(query);
			List<Result> list = queryResult.getResults();
			System.out.println(String.format("list.size():%d", list.size()));
			for(Result element : list) {
				System.out.println("@@@@@" + element);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			influxDb.close();
		}
	}


}
