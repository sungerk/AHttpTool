package org.sunger.lib.http.asyn;


public abstract class TextResponseHandler extends AsyncHttpResponseHandler {
	abstract public void onSuccess(int stateCode, String data);

	@Override
	public void onSuccess(int stateCode, byte[] data) {
		onSuccess(stateCode, new String(data));
	}

}
