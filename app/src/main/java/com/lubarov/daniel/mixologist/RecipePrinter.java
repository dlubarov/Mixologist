package com.lubarov.daniel.mixologist;

import android.content.Context;
import android.os.Build;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lubarov.daniel.mixologist.model.Recipe;

import java.util.HashSet;
import java.util.Set;

/**
 * Prints recipes.
 */
public class RecipePrinter {
    private static final String TAG = RecipePrinter.class.getSimpleName();

    /**
     * Holds references to WebViews simply to prevent them from being garbage collected.
     * See https://developer.android.com/training/printing/html-docs.html
     */
    private static final Set<WebView> activeWebViews = new HashSet<>();

    public static void print(final Recipe recipe, final Context context) {
        String html = getHtml(recipe);

        final WebView webView = new WebView(context);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView _webView, String url) {
                PrintManager printManager = (PrintManager) context.getSystemService(Context.PRINT_SERVICE);

                PrintDocumentAdapter printAdapter;
                if (Build.VERSION.SDK_INT >= 21) {
                    printAdapter = webView.createPrintDocumentAdapter(recipe.getName());
                } else {
                    printAdapter = webView.createPrintDocumentAdapter();
                }

                String jobName = recipe.getName();
                printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
                activeWebViews.remove(webView);
            }
        });

        webView.loadDataWithBaseURL(null, html, "text/HTML", "UTF-8", null);

        activeWebViews.add(webView);
    }

    private static String getHtml(Recipe recipe) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>");

        htmlBuilder.append("<head>");
        htmlBuilder.append("<style type=\"text/css\">");
        htmlBuilder.append("body { font-family: Garamond, Palatino, Serif; }");
        htmlBuilder.append("h1 { font-size: 200%; }");
        htmlBuilder.append("h2 { font-size: 120%; }");
        htmlBuilder.append("li { font-size: 90%; }");
        htmlBuilder.append("</style>");
        htmlBuilder.append("</head>");

        htmlBuilder.append("<body>");
        htmlBuilder.append("<h1>").append(recipe.getName()).append("</h1>");

        htmlBuilder.append("<h2>").append("Ingredients").append("</h2>");
        htmlBuilder.append("<ul>");
        for (String ingredient : recipe.getIngredientDescriptions()) {
            htmlBuilder.append("<li>").append(ingredient).append("</li>");
        }
        htmlBuilder.append("</ul>");

        htmlBuilder.append("<h2>").append("Instructions").append("</h2>");
        htmlBuilder.append("<ul>");
        for (String step : recipe.getSteps()) {
            htmlBuilder.append("<li>").append(step).append("</li>");
        }
        htmlBuilder.append("</ul>");

        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");
        return htmlBuilder.toString();
    }
}
