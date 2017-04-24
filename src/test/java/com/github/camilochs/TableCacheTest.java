package com.github.camilochs;

import java.io.File;

import org.junit.Ignore;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */

public class TableCacheTest  extends TestCase
{
	private static TableCache cache = new TableCache();
    public TableCacheTest( String testName )
    {
        super( testName );
    }
    
    /**
     * Load data from files .tbl(TPC-H)
     */
    @Ignore
    public void testLoadData()
    {   
        new File("table_stat.txt").delete();
        cache.loadDataFromTPCH();    
    }
    
    //Without optimization
    /**
     * Select query
     */
    @Ignore
    public void testSelectQuery()
    {
    	long tStart = System.currentTimeMillis();
    	
        cache.querySelect();   
        
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(String.format("Total time: %s seconds.", elapsedSeconds));
    }
    /**
     * Max(column_table) test.
     */
    @Ignore
    public void testAggregationMaxQuery()
    {
    	long tStart = System.currentTimeMillis();
        
    	cache.queryAggregationMax();
        
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(String.format("Total time: %s seconds.", elapsedSeconds));  
    }
    /**
     * Min(column_table) test.
     */
    @Ignore
    public void testAggregationMinQuery()
    {
    	long tStart = System.currentTimeMillis();
        
    	cache.queryAggregationMin(); 
        
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(String.format("Total time: %s seconds.", elapsedSeconds));  
    }
    /**
     * Avg(column_table) test.
     */
    @Ignore
    public void testAggregationAvgQuery()
    {
    	long tStart = System.currentTimeMillis();
        
    	cache.queryAggregationAvg();
        
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(String.format("Total time: %s seconds.", elapsedSeconds));  
    }
    
    /**
     * Sum(column_table) test.
     */
    @Ignore
    public void testAggregationSumQuery()
    {
    	long tStart = System.currentTimeMillis();
        
    	cache.queryAggregationSum();
        
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(String.format("Total time: %s seconds.", elapsedSeconds));  
    }

    /**
     * distinct(column_table) test.
     */
    @Ignore
    public void testDistinctQuery()
    {
    	long tStart = System.currentTimeMillis();
        
    	cache.queryDistinctValue();
        
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(String.format("Total time: %s seconds.", elapsedSeconds));  
    }
    /**
     * Join test #1
     */
    @Ignore
    public void testJoinQueryOne()
    {
    	long tStart = System.currentTimeMillis();
    	
        cache.queryJoinOne(); 
        
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(String.format("Total time: %s seconds.", elapsedSeconds));  
    }
    /**
     * Join test #2
     */
    @Ignore
    public void testJoinQueryTwo()
    {
    	long tStart = System.currentTimeMillis();
    	
        cache.queryJoinTwo();  
        
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(String.format("Total time: %s seconds.", elapsedSeconds));  
    }
    public void testQueryWithoutOptimization(){
    	System.out.println("Normal---------------");
    	this.testSelectQuery();
    	this.testAggregationMaxQuery();
    	this.testAggregationMinQuery();
    	this.testAggregationAvgQuery();
    	this.testDistinctQuery();
    	this.testJoinQueryOne();
    	this.testJoinQueryTwo();
    }
    //With optimization
    
    /**
     * Select query
     */
    @Ignore
    public void testSelectQueryOpt()
    {
    	long tStart = System.currentTimeMillis();
        cache.querySelectOpt(9999999);  
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(String.format("Total time: %s seconds.", elapsedSeconds));
    }
    /**
     * Max(column_table) test.
     */
    @Ignore
    public void testAggregationMaxQueryOpt()
    {
    	long tStart = System.currentTimeMillis();
        
    	cache.queryAggregationMaxOpt();
        
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(String.format("Total time: %s seconds.", elapsedSeconds));  
    }
    /**
     * Min(column_table) test.
     */
    @Ignore
    public void testAggregationMinQueryOpt()
    {
    	long tStart = System.currentTimeMillis();
        
    	cache.queryAggregationMinOpt();
        
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(String.format("Total time: %s seconds.", elapsedSeconds));  
    }
    /**
     * Distinct(column_table) test.
     */
    @Ignore
    public void testAggregationDistinctQueryOpt()
    {
    	long tStart = System.currentTimeMillis();
        
    	cache.queryDistinctValueOpt();
        
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(String.format("Total time: %s seconds.", elapsedSeconds));  
    }
    
    public void testQueryWithOptimization(){
    	System.out.println("With Optimization---------------");
    	this.testSelectQueryOpt();
    	this.testAggregationMaxQueryOpt();
    	this.testAggregationMinQueryOpt();
    	this.testAggregationDistinctQueryOpt();
    	
    }
    
}
