package com.lubarov.daniel.mixologist;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.util.WeakHashMap;

public class ThumbnailCache {
    public static final ThumbnailCache LARGE = new ThumbnailCache(160);

    private final int maxThumbnailSizePixels;
    private final WeakHashMap<Integer, Bitmap> thumbnailsByResourceId;

    private ThumbnailCache(int maxThumbnailSizePixels) {
        this.maxThumbnailSizePixels = maxThumbnailSizePixels;
        thumbnailsByResourceId = new WeakHashMap<>();
    }

    public synchronized Bitmap getThumbnail(Resources resources, int resourceId) {
        Bitmap thumbnail = thumbnailsByResourceId.get(resourceId);
        if (thumbnail == null)
            thumbnailsByResourceId.put(resourceId, thumbnail = createThumbnail(resources, resourceId));
        return thumbnail;
    }

    private Bitmap createThumbnail(Resources resources, int resourceId) {
        Bitmap originalBitmap = BitmapFactory.decodeResource(resources, resourceId);
        int minDimension = Math.min(originalBitmap.getWidth(), originalBitmap.getHeight());
        int trimX = originalBitmap.getWidth() - minDimension;
        int trimY = originalBitmap.getHeight() - minDimension;

        float scaleFactor = Math.min(maxThumbnailSizePixels / (float) minDimension, 1f);
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
