package tests;

import cn.com.hoonsoft.R;
import cn.com.hoonsoft.tool.ToolFile;
import android.test.AndroidTestCase;
import android.util.Log;

public class ToolFileTest extends AndroidTestCase {

	private static final String TAG = ToolFileTest.class.getSimpleName();
	
	public void testReadRawValue() throws Throwable{
		String result = ToolFile.readRawValue(getContext(), R.raw.test);
		Log.i(TAG, result);
	}
	
}
