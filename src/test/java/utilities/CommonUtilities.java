package utilities;

import java.util.UUID;

public class CommonUtilities {
	public static String getRandomString() {

	    UUID randomUUID = UUID.randomUUID();

	    return randomUUID.toString().replaceAll("_", "");

	  }

}
