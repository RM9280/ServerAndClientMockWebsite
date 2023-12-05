import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;

class Test_viewOrders {

	@Test
	void test() {
		StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
		StoreThread test = new StoreThread();
		test.viewOrders(printWriter);
        String serverRes = writer.toString();
    	assertEquals("45\nLaptop\n2\n40\nMouse\n3\n",serverRes);
	}
}
