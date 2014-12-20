package cl.telematica.multimedio.tareamultimedios.adater;

import cl.telematica.multimedio.tareamultimedios.R;
import cl.telematica.multimedio.tareamultimedios.app.AppController;
import cl.telematica.multimedio.tareamultimedios.model.Element;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Element> elementos;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Element> movieItems) {
		this.activity = activity;
		this.elementos = movieItems;
	}

	@Override
	public int getCount() {
		return elementos.size();
	}

	@Override
	public Object getItem(int location) {
		return elementos.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView image = (NetworkImageView) convertView
				.findViewById(R.id.Image);
		TextView title = (TextView) convertView.findViewById(R.id.Title);
		TextView points = (TextView) convertView.findViewById(R.id.Points);



		Element m = elementos.get(position);

		// thumbnail image
		image.setImageUrl(m.getImage(), imageLoader);
		
		// title
		title.setText(m.getTitle());
		
		// points
		points.setText(String.valueOf(m.getPoints()));
		


		return convertView;
	}

}