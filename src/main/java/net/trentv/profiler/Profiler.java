package net.trentv.profiler;

import java.util.ArrayList;
import java.util.HashMap;

public class Profiler
{
	static class Section
	{
		String identifier;
		long startTime = -1;
		long endTime = -1;
		
		public Section(String identifier)
		{
			this.identifier = identifier;
			this.startTime = System.nanoTime();
		}
		
		public void end()
		{
			this.endTime = System.nanoTime();
		}
		
		public long getTime()
		{
			if(endTime < 0 | startTime < 0)
			{
				return -1;
			}
			return endTime - startTime;
		}
	}
	
	static HashMap<String, ArrayList<Section>> a = new HashMap<String, ArrayList<Section>>();
	
	public static void startSection(String identifier)
	{
		Section currentSection = new Section(identifier);
		ArrayList<Section> sectionList = a.get(identifier);
		if(sectionList == null)
		{
			ArrayList<Section> newList = new ArrayList<Section>();
			newList.add(currentSection);
			a.put(identifier, newList);
		}
		else
		{
			sectionList.add(currentSection);
		}
	}
	
	public static void endSection(String identifier)
	{
		ArrayList<Section> sectionList = a.get(identifier);
		if(sectionList == null) System.out.println("PROFILER : ERROR : NO VALID SECTION DEFINED : " + identifier);
		else
		{
			sectionList.get(sectionList.size()-1).end();
		}
	}
	
	public static int getIterations(String identifier)
	{
		ArrayList<Section> sectionList = a.get(identifier);
		if(sectionList == null) System.out.println("PROFILER : ERROR : NO VALID SECTION DEFINED : " + identifier);
		else
		{
			return sectionList.size();
		}
		return -1;
	}
	
	public static long getAverageTime(String identifier)
	{
		ArrayList<Section> sectionList = a.get(identifier);
		if(sectionList == null) System.out.println("PROFILER : ERROR : NO VALID SECTION DEFINED : " + identifier);
		else
		{
			long total = 0;
			for(int i = 0; i < sectionList.size(); i++)
			{
				total += sectionList.get(i).getTime();
			}
			return total / sectionList.size();
		}
		return -1;
	}
	
	public static long getLastTime(String identifier)
	{
		ArrayList<Section> sectionList = a.get(identifier);
		if(sectionList == null) System.out.println("PROFILER : ERROR : NO VALID SECTION DEFINED : " + identifier);
		else
		{
			return sectionList.get(sectionList.size()-1).getTime();
		}
		return -1;
	}
	
	public static long getMinTime(String identifier)
	{
		ArrayList<Section> sectionList = a.get(identifier);
		if(sectionList == null) System.out.println("PROFILER : ERROR : NO VALID SECTION DEFINED : " + identifier);
		else
		{
			long min = Long.MAX_VALUE;
			for(int i = 0; i < sectionList.size(); i++)
			{
				if(min > sectionList.get(i).getTime())
				{
					min = sectionList.get(i).getTime();
				}
			}
			return min;
		}
		return -1;
	}
	
	public static long getMaxTime(String identifier)
	{
		ArrayList<Section> sectionList = a.get(identifier);
		if(sectionList == null) System.out.println("PROFILER : ERROR : NO VALID SECTION DEFINED : " + identifier);
		else
		{
			long max = Long.MIN_VALUE;
			for(int i = 0; i < sectionList.size(); i++)
			{
				if(max < sectionList.get(i).getTime())
				{
					max = sectionList.get(i).getTime();
				}
			}
			return max;
		}
		return -1;
	}
}
