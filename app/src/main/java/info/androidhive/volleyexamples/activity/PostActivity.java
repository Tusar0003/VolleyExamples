package info.androidhive.volleyexamples.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import info.androidhive.volleyexamples.R;
import info.androidhive.volleyexamples.app.AppController;

public class PostActivity extends AppCompatActivity {

    private static final String TAG = "PostActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final EditText nameEditText = (EditText) findViewById(R.id.name);
        final TextView responseTextView = (TextView) findViewById(R.id.msgResponse);
        findViewById(R.id.btnPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(nameEditText.getText())) {
                    // Tag used to cancel the request
                    String tag_json_obj = "json_obj_req";
                    String url = String.format("https://www.googleapis.com/books/v1/volumes?q=%1$s",
                            nameEditText.getText().toString().trim());

                    final ProgressDialog pDialog = new ProgressDialog(PostActivity.this);
                    pDialog.setMessage("Loading...");
                    pDialog.show();

                    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.e(TAG, response.toString());
                                    responseTextView.setText(response.toString());
                                    pDialog.hide();
                                }
                            }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.e(TAG, "Error: " + error.getMessage());
                            pDialog.hide();
                        }
                    });

                    // Adding request to request queue
                    AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
                }
            }
        });

//        findViewById(R.id.btnPost).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!TextUtils.isEmpty(nameEditText.getText())) {
//                    // Tag used to cancel the request
//                    String tag_json_obj = "json_obj_req";
////                    String url = "https://api.androidhive.info/volley/person_object.json";
//                    String url = "http://httpbin.org/post";
//
//                    final ProgressDialog pDialog = new ProgressDialog(PostActivity.this);
//                    pDialog.setMessage("Loading...");
//                    pDialog.show();
//
//                    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, null,
//                            new Response.Listener<JSONObject>() {
//
//                                @Override
//                                public void onResponse(JSONObject response) {
//                                    Log.d(TAG, response.toString());
//                                    responseTextView.setText(response.toString());
//                                    pDialog.hide();
//                                }
//                            }, new Response.ErrorListener() {
//
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            VolleyLog.e(TAG, "Error: " + error.getMessage());
//                            pDialog.hide();
//                        }
//                    }) {
//
//                        @Override
//                        protected Map<String, String> getParams() {
//                            Log.e(TAG, "getParams: called");
//
////                            Map<String, String> params = new HashMap<String, String>();
////                            params.put("name", "Androidhive");
////                            params.put("email", "abc@androidhive.info");
////                            params.put("password", "password123");
//
//                            Map<String, String>  params = new HashMap<String, String>();
//                            params.put("name", "Alif");
//                            params.put("domain", "http://itsalif.info");
//                            Log.e(TAG, "getParams: params: " + params);
//
//                            return params;
//                        }
//
//                    };
//
//                    // Adding request to request queue
//                    AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
//                }
//            }
//        });
    }
}
