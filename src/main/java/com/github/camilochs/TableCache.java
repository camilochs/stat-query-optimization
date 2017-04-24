package com.github.camilochs;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.*;

public class TableCache 
{
	private final String fileNameCustomerTable = new File(getClass().getClassLoader().getResource("customer.tbl").getFile()).getAbsolutePath();
	private final String fileNameNationTable = new File(getClass().getClassLoader().getResource("nation.tbl").getFile()).getAbsolutePath();
	private final String fileNameOrderTable = new File(getClass().getClassLoader().getResource("orders.tbl").getFile()).getAbsolutePath();
	
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private ArrayList<Nation> nations = new ArrayList<Nation>();
	private ArrayList<Order> orders = new ArrayList<Order>();
	private TableStatistic stat = new TableStatistic();
	private TableColumnInfo tci = null;
	
	/**
     * Load data file to memory.
     */
	private boolean loadFileCustomerTable(String fileName){
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			br.lines().collect(Collectors.toList())
				.stream()
				.map(m -> m.toString())
				.forEach(line ->{
					String[] data = line.split("\\|");
					Customer customer = new Customer();
					customer.setKey(Long.parseLong(data[0]));
					customer.setName(data[1]);
					customer.setAddress(data[2]);
					customer.setNationKey(Long.parseLong(data[3]));
					customer.setPhone(data[4]);
					customer.setAccountBalance(Double.parseDouble(data[5]));
					customer.setMktSegment(data[6]);
					customer.setComment(data[7]);
					customers.add(customer);
			});
			System.out.println(String.format("Total rows of [Customer Table]: %s", customers.size()));
			
			String tableName = "Customer";
			int totalRow = customers.size();
			int totalColumn = Customer.class.getDeclaredFields().length;
			
			stat.create(new TableInfo(tableName, totalRow, totalColumn), null);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return false;
		}
		return true;
	}
	/**
     * Load data file to memory.
     */
	private boolean loadFileOrderTable(String fileName){
		
		HashSet<Long> distinctValueKey = new HashSet<Long>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			br.lines().collect(Collectors.toList())
				.stream()
				.map(m -> m.toString())
				.forEach(line ->{
					String[] data = line.split("\\|");
					Order order = new Order();
					
					order.setKey(Long.parseLong(data[0]));
					distinctValueKey.add(order.getKey().longValue());
					
					order.setCustomerKey(Long.parseLong(data[1]));
					order.setOrderStatus(data[2]);
					order.setTotalPrice(Double.parseDouble(data[3]));
					try {
						order.setOrderDate(new SimpleDateFormat("yyyy-mm-dd").parse(data[4]));
					} catch (Exception e) {
						e.printStackTrace();
					}
					order.setOrderPriority(data[5]);
					order.setClerkKey(Long.parseLong(data[6].split("#")[1]));
					order.setShipPriority(Integer.parseInt(data[7]));
					order.setComment(data[8]);
					orders.add(order);
					
			});
			System.out.println(String.format("Total rows of [Orders Table]: %s", orders.size()));
			
			String tableName = "Orders";
			int totalRow = orders.size();
			int totalColumn = Order.class.getDeclaredFields().length;
			
			//Stat by columns, example column: total_price
			
			List<TableColumnInfo> tableColumnInfo = new ArrayList<TableColumnInfo>();
			tci = new TableColumnInfo();
			tci.setColumnName("Total_price");
			tci.setDataType("Double");
			tci.setMaxValue(this.queryAggregationMax());
			tci.setMinValue(this.queryAggregationMin());
			tci.setAverageValue(this.queryAggregationAvg());
			tci.setTotalNull(this.queryNullValue());
			tci.setDistinctValue(distinctValueKey.size());
			tableColumnInfo.add(tci);
			
			stat.create(new TableInfo(tableName, totalRow, totalColumn), tableColumnInfo);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return false;
		}
		return true;
	}
	/**
     * Load data file to memory.
     */
	private boolean loadFileNationTable(String fileName){
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			br.lines().collect(Collectors.toList())
				.stream()
				.forEach(line -> {
					String[] data = line.split("\\|");
					Nation order = new Nation();
					order.setKey(Long.parseLong(data[0]));
					order.setName(data[1]);
					order.setRegionKey(Long.parseLong(data[2]));
					order.setComment(data[3]);
					nations.add(order);
			});
			System.out.println(String.format("Total rows of [Nation Table]: %s", nations.size()));
			String tableName = "Nation";
			int totalRow = nations.size();
			int totalColumn = Nation.class.getDeclaredFields().length;
			
			stat.create(new TableInfo(tableName, totalRow, totalColumn), null);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return false;
		}
		return true;
	}
	/**
     * Test laod data from tpc-h
     */
	public boolean loadDataFromTPCH(){
	    
    	return  loadFileCustomerTable(fileNameCustomerTable) && 
    			loadFileOrderTable(fileNameOrderTable) && 
    			loadFileNationTable(fileNameNationTable);
    } 
	/**
     * Test with select predicate.
     */ 
	public void querySelect(){
		
		if(customers.size() == 0){
			return;
		}
		long bigNumber = 99999999;
		List<Customer> filterCustomer = customers
				.stream()
				.filter(m -> m.getKey().longValue() > bigNumber)
				.collect(Collectors.toList());

		System.out.println(String.format("[Selection] Rows found: %s", filterCustomer.size()));
		
	}
	/**
     * Test with select predicate.
     */ 
	public void querySelectOpt(long number){
		
		if(customers.size() == 0){
			return;
		}
		List<Customer> filterCustomer = new ArrayList<Customer>();
		if(tci.getMaxValue() >= number){
			filterCustomer = customers
					.stream()
					.filter(m -> m.getKey().longValue() > number)
					.collect(Collectors.toList());
		}

		System.out.println(String.format("[Selection] Rows found: %s", filterCustomer.size()));
		
	}
	/**
     * Test with distinct function.
     */
	public long queryDistinctValue(){
		
		if(orders.size() == 0){
			return -1; 
		}
		long distinctTotal = orders
				.stream()
				.mapToDouble(m -> m.getTotalPrice())
				.distinct().count();
		
		System.out.println(String.format("[Distinct value] total_price: %s", distinctTotal));	
		return distinctTotal;
	}
	/**
     * Test with distinct function.
     */
	public long queryDistinctValueOpt(){
		
		if(tci == null){
			return -1; 
		}
		System.out.println(String.format("[Distinct value] total_price: %s", tci.getDistinctValue()));	
		return tci.getDistinctValue();
	}
	/**
     * Test with max function.
     */
	public long queryNullValue(){
		
		if(orders.size() == 0){
			return -1; 
		}
		long totalNull = orders
				.stream()
				.filter(m -> m.getTotalPrice() == null)
				.count();
				
		System.out.println(String.format("[Null value] total_price: %s", totalNull));	
		return totalNull;
	}
	/**
     * Test with max function.
     */
	public double queryAggregationMax(){
		
		if(orders.size() == 0){
			return -1; 
		}
		double maxTotalPriceOrder = orders
				.stream()
				.mapToDouble(m -> m.getTotalPrice())
				.max()
				.getAsDouble();
		
		System.out.println(String.format("[Agregation Max] total_price: %s", maxTotalPriceOrder));	
		return maxTotalPriceOrder;
	}
	/**
     * Test with max function.
     */
	public double queryAggregationMaxOpt(){
		
		if(tci == null){
			return -1; 
		}
		
		System.out.println(String.format("[Agregation Max] total_price: %s", tci.getMaxValue()));	
		return tci.getMaxValue();
	}
	/**
     * Test with min function.
     */
	public double queryAggregationMin(){
		
		if(orders.size() == 0){
			return -1;
		}
		double minTotalPriceOrder = orders
				.stream()
				.mapToDouble(m -> m.getTotalPrice())
				.min()
				.getAsDouble();
		
		System.out.println(String.format("[Agregation Min] total_price: %s", minTotalPriceOrder));
		return minTotalPriceOrder;
	}
	/**
     * Test with max function.
     */
	public double queryAggregationMinOpt(){
		
		if(tci == null){
			return -1; 
		}
		
		System.out.println(String.format("[Agregation Min] total_price: %s", tci.getMinValue()));	
		return tci.getMinValue();
	}
	/**
     * Test with sum function.
     */
	public double queryAggregationSum(){
		
		if(orders.size() == 0){
			return -1;
		}
		double sumTotalPrice = orders
				.stream()
				.mapToDouble(m -> m.getTotalPrice())
				.sum();
		
		System.out.println(String.format("[Agregation Sum] total_price: %s", sumTotalPrice));
		return sumTotalPrice;
	}
	

	/**
     * Test with avg function.
     */
	public double queryAggregationAvg(){
		
		if(orders.size() == 0){
			return -1;
		}
		double avgTotalPrice = orders
				.stream()
				.mapToDouble(m -> m.getTotalPrice())
				.average()
				.getAsDouble();
		
		System.out.println(String.format("[Agregation AVG] total_price: %s", avgTotalPrice));
		return avgTotalPrice;
	}
	
	interface OrderOperation {
		boolean operation(long a);
	}

	/**
     * Join test: Customer and Order tables.
     */
	public void queryJoinOne(){
		
		if(customers.size() == 0){
			return;
		}
		
		OrderOperation findCustomerInOrders = (long customerKey) -> {
			return orders.stream().anyMatch(m -> m.getCustomerKey() == customerKey);
		};
										
		List<Customer> getCustomerWithOrders = customers
				.stream()
				.filter(m -> m.getKey() < 200)
				.filter(m -> findCustomerInOrders.operation(m.getKey().longValue()))
				.collect(Collectors.toList());
		System.out.println(String.format("[Join Customer and Orders tables] Rows found: %s", getCustomerWithOrders.size()));
		
	}
	
	interface CustomerOperation {
		boolean operation(long a);
	}
	/**
     * Join test: Customer and Nation tables.
     */
	public void queryJoinTwo(){
		
		if(customers.size() == 0){
			return;
		}
		
		CustomerOperation findCustomerInNation = (long nationKey) -> {
			
			return customers.stream().anyMatch(m -> m.getKey() == nationKey);
		};
										
		List<Nation> getNationsWithCustomers = nations
				.stream()
				.filter(m -> findCustomerInNation.operation(m.getKey().longValue()))
				.collect(Collectors.toList());
		System.out.println(String.format("[Join Nations and Customers tables] Rows found: %s", getNationsWithCustomers.size()));
		
	}
	
 
}
