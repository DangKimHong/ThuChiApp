package com.chitieu.chitieuwebapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String url = Common.urlChiTieuApi;
    ListView lvChiTieu;
    ArrayList<ChiTieu> chiTieuArrayList;
    ChiTieuAdapter chiTieuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvChiTieu = (ListView) findViewById(R.id.listviewChiTieu);
        chiTieuArrayList = new ArrayList<>();
        chiTieuAdapter = new ChiTieuAdapter(this, R.layout.dong_chi_tieu, chiTieuArrayList);

        lvChiTieu.setAdapter(chiTieuAdapter);
        GetListData(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_chitieu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuThemChiTieu) {
            startActivity(new Intent(MainActivity.this, AddChiTieuActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void GetListData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        chiTieuArrayList.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                chiTieuArrayList.add(new ChiTieu(
                                        object.getLong("ID"),
                                        object.getString("Ten"),
                                        object.getLong("SoTien"),
                                        object.getString("Ngay")));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        chiTieuAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void DeleteData(long id) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE,
                url + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().contains("success")) {
                            Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Lỗi xóa!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                        Log.d("Error", "Lỗi \n" + error.toString());
                    }
                }
        );
        requestQueue.add(stringRequest);
    }
}
