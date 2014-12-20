package cl.telematica.multimedio.tareamultimedios;


import cl.telematica.multimedio.tareamultimedios.app.AppController;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


import android.app.Activity;

import android.os.Bundle;


public class Detalles  extends Activity {
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detalles);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView image = (NetworkImageView) this
				.findViewById(R.id.Image);

		//Recuperamos la informaci�n pasada en el intent
		final Bundle bundle = this.getIntent().getExtras();



		image.setImageUrl(bundle.getString("image"), imageLoader);

		 


	}




}

