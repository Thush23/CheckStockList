package com.example.stockchecklist;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvstatus;
    EditText etStock;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvstatus = findViewById(R.id.tvstatus);
        etStock = findViewById(R.id.etStock);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSearch:
                searchClicked();
                break;
        }
    }
    private void searchClicked() {
        String Symbol = etStock.getText().toString();
        String url = "https://api.iextrading.com/1.0/stock/"+ Symbol +"/quote";
        try{
            new APIWorker().execute(new URL(url));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    class APIWorker extends AsyncTask<URL, Void, String > {
        @Override
        protected String doInBackground(URL... urls) {
            StringBuilder result = new StringBuilder();
            try {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection)urls[0].openConnection();
                InputStream inputStream = new BufferedInputStream(httpsURLConnection.getInputStream());

                BufferedReader buffedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = buffedReader.readLine()) != null) {
                    result.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result.toString();
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String results) {
            super.onPostExecute(results);
            Gson gson = new Gson();
            IexTradingStocks iexTradingStocks = gson.fromJson(results, IexTradingStocks.class);

            String Symbol = iexTradingStocks.getSymbol();
            String CompanyName = iexTradingStocks.getCompanyName();
            String PrimaryExchange = iexTradingStocks.getPrimaryExchange();
            double IexRealTimePrice = iexTradingStocks.getIexRealTimePrice();

            String info =   "Symbol: "+ Symbol + "\n" +
                    "Company_Name: " +CompanyName + "\n" +
                    "Primary_Exchange: " + PrimaryExchange+ "\n" +
                    "Real_Time_Price: " + IexRealTimePrice;
            tvstatus.setText(info);
        }
    }
}
