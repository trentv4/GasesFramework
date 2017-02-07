package net.trentv.gfapi;

public class GFAPI
{
	public static IManipulationAPI manipulationAPI;
	public static IRegistrationAPI registrationAPI;
	
	public static void install(IManipulationAPI a, IRegistrationAPI b)
	{
		manipulationAPI = a;
		registrationAPI = b;
	}
	
	public static boolean isInstalled()
	{
		return (manipulationAPI != null & registrationAPI != null);
	}
}
