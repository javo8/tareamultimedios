package cl.telematica.multimedio.tareamultimedios;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cl.telematica.multimedio.tareamultimedios.app.AppController;
import cl.telematica.multimedio.tareamultimedios.model.Element;
import cl.telematica.multimedio.tareamultimedios.adater.CustomListAdapter;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.facebook.AppEventsLogger;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends FragmentActivity {
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String url = "http://www.mocky.io/v2/5440667984d353f103f697c0";
	private ProgressDialog pDialog;
	private List<Element> elementos = new ArrayList<Element>();
	private ListView listView;
	private CustomListAdapter adapter;
    private MainFragment mainFragment;
    private static final String TAGO = "MainFragment";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, mainFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            mainFragment = (MainFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }

		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, elementos);
		listView.setAdapter(adapter);

		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Cargando contenido...");
		pDialog.show();
		
		listView.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
	          	    Object o = adapter.getItem(position);
	                Element obj_itemDetails = (Element)o;

	                Intent intent = new Intent(MainActivity.this, Detalles.class);
	            	Bundle b = new Bundle(); 
	            	b.putString("image", obj_itemDetails.getImage());
	            	intent.putExtras(b);
	                startActivity(intent);   
	     }
	});
		JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d("Json", response.toString());	
						hidePDialog();

						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								Element elem = new Element();
								elem.setTitle(obj.getString("title"));
								elem.setImage(obj.getString("image"));
								elem.setPoints(obj.getString("points"));
								elementos.add(elem);

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}
						adapter.notifyDataSetChanged();
					
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d("Error", "Error: " + error.getMessage());

					}
				});

		AppController.getInstance().addToRequestQueue(request);
		
	}

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

}
