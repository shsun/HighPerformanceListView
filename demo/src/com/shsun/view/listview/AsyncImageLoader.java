package com.shsun.view.listview;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;

import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AsyncImageLoader {

    public static final String TAG = "AsyncImageLoader";

    private HashMap<String, SoftReference<Drawable>> mImageCacher;

    public AsyncImageLoader() {
        mImageCacher = new HashMap<String, SoftReference<Drawable>>();
    }

    public Drawable loadDrawable(final int position, final String imageUrl,
            final OnImageLoaderListener imgLoaderListener) {
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
                imgLoaderListener.onSuccess((Drawable) message.obj, imageUrl);
            }
        };
        new Thread() {
            @Override
            public void run() {
                Drawable drawable;
                try {
                    drawable = loadImageFromUrl(imageUrl);
                    mImageCacher.put(imageUrl, new SoftReference<Drawable>(drawable));
                    Message message = handler.obtainMessage(0, drawable);
                    handler.sendMessage(message);
                } catch (IOException e) {
                    imgLoaderListener.onFail(imageUrl);
                }
            }
        }.start();
        return null;
    }

    public static Drawable loadImageFromUrl(String url) throws IOException {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File f = new File(Environment.getExternalStorageDirectory() + "/TestSyncListView/" + url);
            if (f.exists()) {
                FileInputStream fis = new FileInputStream(f);
                Drawable d = Drawable.createFromStream(fis, "src");
                return d;
            }
            URL m = new URL(url);
            InputStream i = (InputStream) m.getContent();
            DataInputStream in = new DataInputStream(i);
            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteread);
            }
            in.close();
            out.close();
            Drawable d = Drawable.createFromStream(i, "src");
            return loadImageFromUrl(url);
        } else {
            URL m = new URL(url);
            InputStream i = (InputStream) m.getContent();
            Drawable d = Drawable.createFromStream(i, "src");
            return d;
        }

    }

    public interface OnImageLoaderListener {

        public void onSuccess(Drawable imgDrawable, String url);

        public void onFail(String url);

    }

}
