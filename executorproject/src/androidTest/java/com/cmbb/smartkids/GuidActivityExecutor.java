package com.cmbb.smartkids;

import android.app.Activity;
import com.robotium.recorder.executor.Executor;

@SuppressWarnings("rawtypes")
public class GuidActivityExecutor extends Executor {

	@SuppressWarnings("unchecked")
	public GuidActivityExecutor() throws Exception {
		super((Class<? extends Activity>) Class.forName("com.cmbb.smartkids.activity.GuidActivity"),  "com.cmbb.smartkids.R.id.", new android.R.id(), false, false, "1445251983384");
	}

	public void setUp() throws Exception { 
		super.setUp();
	}
}
