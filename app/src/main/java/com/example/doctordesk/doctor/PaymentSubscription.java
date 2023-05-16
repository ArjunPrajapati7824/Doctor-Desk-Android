package com.example.doctordesk.doctor;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctordesk.MainActivity;
import com.example.doctordesk.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PaymentSubscription extends AppCompatActivity implements PaymentResultListener {



    PaymentSheet paymentSheet;
    String paymentIntentClientSecret;
    PaymentSheet.CustomerConfiguration configuration;
    Button Pay;
    TextView textView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentsubscription);

        Pay = findViewById(R.id.Pay);
        textView = findViewById(R.id.txtView);
        Pay.setOnClickListener(view -> payment());
    }
        void payment () {

            Checkout checkout = new Checkout();
            checkout.setKeyID("rzp_test_kKNF7f14dldbUk");

//             checkout.setImage(R.drawable.logo);


            final PaymentSubscription activity = this;


            try {
                JSONObject options = new JSONObject();

                options.put("name", "Name");
                options.put("description", "Reference No. #123456");
                // options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
                options.put("theme.color", "#3399cc");
                options.put("currency", "INR");
                options.put("amount", Integer.parseInt(textView.getText().toString()) * 100);//pass amount in currency subunits
                options.put("prefill.email", "projectmates1302@gmail.com");
                options.put("prefill.contact", "7201914901");
                JSONObject retryObj = new JSONObject();
                retryObj.put("enabled", true);
                retryObj.put("max_count", 4);
                options.put("retry", retryObj);

                checkout.open(activity, options);

            } catch (Exception e) {
                Log.e(TAG, "Error in starting Razorpay Checkout", e);
            }
        }

        @Override
        public void onPaymentSuccess (String s){
            Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show();
            DoctorRegistretion.is_payment_successful=true;
            startActivity(new Intent(getApplicationContext(),DoctorRegistretion.class));
        }

        @Override
        public void onPaymentError ( int i, String s){

            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),DoctorRegistretion.class));
        }

//
//
//        Pay=findViewById(R.id.Pay);
//        Pay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(paymentIntentClientSecret!=null)
//                    paymentSheet.presentWithPaymentIntent(paymentIntentClientSecret,new PaymentSheet.Configuration("DoctorDesc",configuration));
//                else
//                    Toast.makeText(PaymentSubscription.this, "Loading....", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        paymentSheet=new PaymentSheet(this,this::onPaymentSheetResult);
//
//    }
//
//    private void onPaymentSheetResult(final PaymentSheetResult paymentSheetResult)
//    {
//        if(paymentSheetResult instanceof PaymentSheetResult.Canceled){
//            startActivity(new Intent(getApplicationContext(),DoctorRegistretion.class));
//            Toast.makeText(this, "Payment canceled", Toast.LENGTH_SHORT).show();
//        }
//        if(paymentSheetResult instanceof PaymentSheetResult.Failed){
//            startActivity(new Intent(getApplicationContext(),DoctorRegistretion.class));
//            Toast.makeText(this, ((PaymentSheetResult.Failed)paymentSheetResult).getError().getMessage(), Toast.LENGTH_SHORT).show();
//        }
//        if(paymentSheetResult instanceof PaymentSheetResult.Completed){
//            Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
//            DoctorRegistretion.is_payment_successful=true;
//            startActivity(new Intent(getApplicationContext(),DoctorRegistretion.class));
//        }
//    }
//
//    public void fetchApi(){
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="https://demo.codeseasy.com/apis/stripe/";
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try{
//                            JSONObject jsonObject=new JSONObject(response);
//                            configuration=new PaymentSheet.CustomerConfiguration(
//                                    jsonObject.getString("customer"),
//                                    jsonObject.getString("ephemeralKey")
//                            );
//                            paymentIntentClientSecret =jsonObject.getString("paymentIntent");
//                            PaymentConfiguration.init(getApplicationContext(),jsonObject.getString("publishableKey"));
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                            Toast.makeText(PaymentSubscription.this, "Done bhai", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                Toast.makeText(PaymentSubscription.this, "Already done", Toast.LENGTH_SHORT).show();
//            }
//        }){
//            protected Map<String, String> getParams(){
//                Map<String, String> paramV = new HashMap<>();
//                paramV.put("authKey", "abc");
//                return paramV;
//            }
//        };
//        queue.add(stringRequest);
//
//    }

}
