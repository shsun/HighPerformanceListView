package cn.wangmeng.test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AsyncImageLoader {

    public static final String TAG = "AsyncImageLoader";
    
    private HashMap<String, SoftReference<Drawable>> mImageCacher;

    public AsyncImageLoader() {
        mImageCacher = new HashMap<String, SoftReference<Drawable>>();
    }

    public Drawable loadDrawable(final int position, final String imageUrl, final ImageCallback imageCallback) {
        Boolean c = mImageCacher.containsKey(imageUrl);
        Log.i(TAG, "loadDrawable, existing=" + c + ", position=" + position + ", url=" + imageUrl);
        if (c) {
            SoftReference<Drawable> softReference = mImageCacher.get(imageUrl);
            Drawable drawable = softReference.get();
            if (drawable != null) {
                return drawable;
            }
        }
        final Handler handler = new Handler() {
            public void handleMessage(Message message) {
                imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
            }
        };
        new Thread() {
            @Override
            public void run() {
                Drawable drawable = loadImageFromUrl(imageUrl);
                mImageCacher.put(imageUrl, new SoftReference<Drawable>(drawable));
                // 
                Message message = handler.obtainMessage(0, drawable);
                handler.sendMessage(message);
            }
        }.start();
        return null;
    }

    public static Drawable loadImageFromUrl(String url) {
        URL m;
        InputStream i = null;
        try {
            m = new URL(url);
            i = (InputStream) m.getContent();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Drawable.createFromStream(i, "src");
    }

    public interface ImageCallback {
        public void imageLoaded(Drawable imageDrawable, String imageUrl);
    }

}
