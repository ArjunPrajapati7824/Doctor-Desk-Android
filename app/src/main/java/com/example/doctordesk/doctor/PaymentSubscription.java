package com.example.doctordesk.doctor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctordesk.R;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PaymentSubscription extends AppCompatActivity {


    PaymentSheet paymentSheet;
    String paymentIntentClientSecret;
    PaymentSheet.CustomerConfiguration configuration;
    Button Pay;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentsubscription);

        fetchApi();

        Pay=findViewById(R.id.Pay);
        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(paymentIntentClientSecret!=null)
                    paymentSheet.presentWithPaymentIntent(paymentIntentClientSecret,new PaymentSheet.Configuration("DoctorDesc",configuration));
                else
                    Toast.makeText(PaymentSubscription.this, "Loading....", Toast.LENGTH_SHORT).show();

            }
        });
        paymentSheet=new PaymentSheet(this,this::onPaymentSheetResult);

    }

    private void onPaymentSheetResult(final PaymentSheetResult paymentSheetResult)
    {
        if(paymentSheetResult instanceof PaymentSheetResult.Canceled){
            startActivity(new Intent(getApplicationContext(),DoctorRegistretion.class));
            Toast.makeText(this, "Payment canceled", Toast.LENGTH_SHORT).show();
        }
        if(paymentSheetResult instanceof PaymentSheetResult.Failed){
            startActivity(new Intent(getApplicationContext(),DoctorRegistretion.class));
            Toast.makeText(this, ((PaymentSheetResult.Failed)paymentSheetResult).getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
        if(paymentSheetResult instanceof PaymentSheetResult.Completed){
            Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
            DoctorRegistretion.is_payment_successful=true;
            startActivity(new Intent(getApplicationContext(),DoctorRegistretion.class));
        }
    }

    public void fetchApi(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://demo.codeseasy.com/apis/stripe/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            configuration=new PaymentSheet.CustomerConfiguration(
                                    jsonObject.getString("customer"),
                                    jsonObject.getString("ephemeralKey")
                            );
                            paymentIntentClientSecret =jsonObject.getString("paymentIntent");
                            PaymentConfiguration.init(getApplicationContext(),jsonObject.getString("publishableKey"));
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(PaymentSubscription.this, "Done bhai", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(PaymentSubscription.this, "Already done", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("authKey", "abc");
                return paramV;
            }
        };
        queue.add(stringRequest);

    }

}
