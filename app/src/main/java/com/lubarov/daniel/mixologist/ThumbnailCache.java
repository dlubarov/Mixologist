package com.lubarov.daniel.mixologist;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.util.WeakHashMap;

public class ThumbnailCache {
    private static final int MAX_THUMBNAIL_SIZE_PIXELS = 300;
    private static final WeakHashMap<Integer, Bitmap> thumbnailsByResourceId;

    static {
        thumbnailsByResourceId = new WeakHashMap<>();
    }

    public static synchronized Bitmap getThumbnail(Resources resources, int resourceId) {
        Bitmap thumbnail = thumbnailsByResourceId.get(resourceId);
        if (thumbnail == null)
            thumbnailsByResourceId.put(resourceId, thumbnail = createThumbnail(resources, resourceId));
        return thumbnail;
    }

    private static Bitmap createThumbnail(Resources resources, int resourceId) {
        Bitmap originalBitmap = BitmapFactory.decodeResource(resources, resourceId);
        int minDimension = Math.min(originalBitmap.getWidth(), originalBitmap.getHeight());
        int trimX = originalBitmap.getWidth() - minDimension;
        int trimY = originalBitmap.getHeight() - minDimension;

        float scaleFactor = Math.min(MAX_THUMBNAIL_SIZE_PIXELS / (float) minDimension, 1f);
        Matrix matrix = new Matrix();
        matrix.postScale(scaleFactor, scaleFactor);

        Bitmap croppedAndScaledBitmap = Bitmap.createBitmap(
                originalBitmap,
                trimX / 2,
                trimY / 2,
                originalBitmap.getWidth() - trimX,
                originalBitmap.getHeight() - trimY,
                matrix, true);
        originalBitmap.recycle();
        return croppedAndScaledBitmap;
    }
}
