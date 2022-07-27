package br.ufpr.tads.webview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private String URL = "http://www.google.com";
    private Page currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Page currentPage = new Page(URL, null,null);
        loadPage(currentPage, true);
    }

    public void loadPage(Page page, Boolean isReload) {
        WebView webView = (WebView) findViewById(R.id.webview);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);
        webView.loadUrl(page.getUrl());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!isReload) {
                    Page newPage = new Page(url, currentPage, null);
                    newPage.setPreviousPage(currentPage);
                    currentPage.setNextPage(newPage);
                    currentPage = newPage;
                } else {
                    currentPage = page;
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });
    }

    public void goBack(View view) {
        if (currentPage.getPreviousPage() != null) {
            currentPage = currentPage.getPreviousPage();
            this.loadPage(currentPage, true);
        }
    }

    public void reload(View view) {
        this.loadPage(currentPage, true);
    }

    public void goForward(View view) {
        if (currentPage.getNextPage() != null) {
            currentPage = currentPage.getNextPage();
            this.loadPage(currentPage, true);
        }
    }

    public class Page {
        public String url;
        public Page previousPage, nextPage;

        public Page(String url, Page previousPage, Page nextPage) {
            this.url = url;
            this.previousPage = previousPage;
            this.nextPage = nextPage;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Page getPreviousPage() {
            return previousPage;
        }

        public void setPreviousPage(Page previousPage) {
            this.previousPage = previousPage;
        }

        public Page getNextPage() {
            return nextPage;
        }

        public void setNextPage(Page nextPage) {
            this.nextPage = nextPage;
        }
    }
}