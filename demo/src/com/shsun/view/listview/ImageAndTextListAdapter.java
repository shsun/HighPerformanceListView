package com.shsun.view.listview;

import java.util.List;

import org.lee.android.R;

import com.shsun.view.listview.AsyncImageLoader.OnImageLoaderListener;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ImageAndTextListAdapter extends ArrayAdapter<ImageAndText> {

    private ListView listView;
    private AsyncImageLoader asyncImageLoader;

    public ImageAndTextListAdapter(Activity activity, List<ImageAndText> imageAndTexts, ListView listView) {
        super(activity, 0, imageAndTexts);
        
        this.listView = listView;
        asyncImageLoader = new AsyncImageLoader();
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) getContext();

        // Inflate the views from XML
        View rowView = convertView;
        ViewCache viewCache;
        if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.image_and_text_row, null);
            viewCache = new ViewCache(rowView);
            rowView.setTag(viewCache);
        } else {
            viewCache = (ViewCache) rowView.getTag();
        }
        ImageAndText imageAndText = getItem(position);

        // Load the image and set it on the ImageView
        String imageUrl = imageAndText.getImageUrl();
        ImageView imageView = viewCache.getImageView();
        imageView.setTag(imageUrl);
        Drawable cachedImage = asyncImageLoader.loadDrawable(position, imageUrl, new OnImageLoaderListener() {
            public void onSuccess(Drawable imageDrawable, String imageUrl) {
                ImageView imageViewByTag = (ImageView) listView.findViewWithTag(imageUrl);
                if (imageViewByTag != null) {
                    imageViewByTag.setImageDrawable(imageDrawable);
                }
            }

            @Override
            public void onFail(String imageUrl) {
                // TODO Auto-generated method stub
                
            }
        });
        // 
        if (cachedImage == null) {
            imageView.setImageResource(R.drawable.last);
        } else {
            imageView.setImageDrawable(cachedImage);
        }
        // Set the text on the TextView
        TextView textView = viewCache.getTextView();
        textView.setText(imageAndText.getText());

        return rowView;
    }

}
