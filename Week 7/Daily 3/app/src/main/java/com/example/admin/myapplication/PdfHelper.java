package com.example.admin.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PdfHelper {

    private static final String FILENAME = "dummy_pdf.pdf";
    private PdfRenderer.Page mCurrentPage;

    Context context;
    private ParcelFileDescriptor fileDescriptor;
    private PdfRenderer renderer;

    public PdfHelper(Context context) {
        this.context = context;
    }

    public void openPdf() throws IOException {
//        ParcelFileDescriptor fileDescriptor =  context.getAssets().openFd("dummy_pdf.pdf").getParcelFileDescriptor();
//        fileDescriptor = ParcelFileDescriptor.dup(context.getResources().openRawResourceFd(R.raw.dummy_pdf).getFileDescriptor());
//        fileDescriptor = context.getAssets().openFd("dummy_pdf.pdf").getParcelFileDescriptor();
//        File pdftoread = new File("app/src/main/res/raw/dummy_pdf.pdf");
//        fileDescriptor = ParcelFileDescriptor.open(pdftoread, ParcelFileDescriptor.MODE_READ_ONLY);
        renderer = new PdfRenderer(fileDescriptor);


        // let us just render all pages
        final int pageCount = renderer.getPageCount();
       // for (int i = 0; i < pageCount; i++) {
            PdfRenderer.Page page = renderer.openPage(0);
            // Use `openPage` to open a specific page in PDF.

            // Important: the destination bitmap must be ARGB (not RGB).
            Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(), mCurrentPage.getHeight(),
                    Bitmap.Config.ARGB_8888);
            // say we render for showing on the screen
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

            // do stuff with the bitmap

            // close the page
//            page.close();
       // }

        // close the renderer
       // renderer.close();
    }

//    private void openRenderer(Context context) throws IOException {
//        // In this sample, we read a PDF from the assets directory.
//        File file = new File(context.getCacheDir(), FILENAME);
//        if (!file.exists()) {
//            // Since PdfRenderer cannot handle the compressed asset file directly, we copy it into
//            // the cache directory.
//            InputStream asset = context.getAssets().open(FILENAME);
//            FileOutputStream output = new FileOutputStream(file);
//            final byte[] buffer = new byte[1024];
//            int size;
//            while ((size = asset.read(buffer)) != -1) {
//                output.write(buffer, 0, size);
//            }
//            asset.close();
//            output.close();
//        }
//        mFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
//        // This is the PdfRenderer we use to render the PDF.
//        if (mFileDescriptor != null) {
//            mPdfRenderer = new PdfRenderer(mFileDescriptor);
//        }
//    }
}
