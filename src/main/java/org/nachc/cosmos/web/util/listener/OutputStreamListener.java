package org.nachc.cosmos.web.util.listener;

import java.io.OutputStream;

import com.nach.core.util.web.listener.Listener;

public class OutputStreamListener implements Listener {

	private OutputStream out;

	public OutputStreamListener(OutputStream out) {
		this.out = out;
	}
	
	@Override
	public OutputStream getOut() {
		return this.getOut();
	}
	
	@Override
	public void notify(Object obj) {
		try {
			String str = obj + "\n";
			this.out.write(str.getBytes());
			out.flush();
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}
