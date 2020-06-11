package com.my.newproject2;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.bumptech.glide.Glide;

public class AllActivity extends Activity {
	
	
	private double sf = 0;
	
	private ArrayList<HashMap<String, Object>> name = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> image = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> type = new ArrayList<>();
	
	private ImageView imageview1;
	private ListView listview1;
	
	private RequestNetwork req;
	private RequestNetwork.RequestListener _req_request_listener;
	private RequestNetwork req1;
	private RequestNetwork.RequestListener _req1_request_listener;
	private RequestNetwork req2;
	private RequestNetwork.RequestListener _req2_request_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.all);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		listview1 = (ListView) findViewById(R.id.listview1);
		req = new RequestNetwork(this);
		req1 = new RequestNetwork(this);
		req2 = new RequestNetwork(this);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (3 == sf) {
					listview1.setAdapter(new Listview1Adapter(name));
					imageview1.setVisibility(View.GONE);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Wait a little");
				}
			}
		});
		
		_req_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _response = _param2;
				image = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				sf++;
				if (3 == sf) {
					listview1.setAdapter(new Listview1Adapter(name));
					imageview1.setVisibility(View.GONE);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Wait a little");
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_req1_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _response = _param2;
				name = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				sf++;
				if (3 == sf) {
					listview1.setAdapter(new Listview1Adapter(name));
					imageview1.setVisibility(View.GONE);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Wait a little");
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_req2_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _response = _param2;
				type = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				sf++;
				if (3 == sf) {
					listview1.setAdapter(new Listview1Adapter(name));
					imageview1.setVisibility(View.GONE);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Wait a little");
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	private void initializeLogic() {
		req.startRequestNetwork(RequestNetworkController.GET, "https://resources-kamakepar.tk/result/image.txt", "image", _req_request_listener);
		req1.startRequestNetwork(RequestNetworkController.GET, "https://resources-kamakepar.tk/result/name.txt", "name", _req1_request_listener);
		req2.startRequestNetwork(RequestNetworkController.GET, "https://resources-kamakepar.tk/result/type.txt", "type", _req2_request_listener);
		sf = 0;
		imageview1.setVisibility(View.VISIBLE);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _view, ViewGroup _viewGroup) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _view;
			if (_v == null) {
				_v = _inflater.inflate(R.layout.list, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _v.findViewById(R.id.imageview1);
			final LinearLayout linear2 = (LinearLayout) _v.findViewById(R.id.linear2);
			final TextView textview1 = (TextView) _v.findViewById(R.id.textview1);
			final TextView textview4 = (TextView) _v.findViewById(R.id.textview4);
			
			textview1.setText(_data.get((int)_position).get("name").toString());
			textview4.setText(type.get((int)_position).get("type").toString());
			Glide.with(getApplicationContext()).load(Uri.parse(image.get((int)_position).get("image").toString())).into(imageview1);
			
			return _v;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
