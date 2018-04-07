package com.chitieu.chitieuwebapi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddChiTieuActivity extends AppCompatActivity {
    EditText edtTen, edtSoTien, edtNgay;
    Button btnThem, btnHuy;
    String urlInsert = Common.urlChiTieuApi;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chi_tieu);

        AnhXa();

        edtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtTen.getText().toString().trim();
                String sotien = edtSoTien.getText().toString().trim();
                String ngay = edtNgay.getText().toString().trim();
                if (ten.isEmpty() || sotien.isEmpty() || ngay.isEmpty()) {
                    Toast.makeText(AddChiTieuActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    ThemChiTieu(urlInsert);
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void ChonNgay() {
        calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                // i: nam, i1: thang, i2: ngay
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtNgay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void ThemChiTieu(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().contains("success")) {
                            Toast.makeText(AddChiTieuActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddChiTieuActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(AddChiTieuActivity.this, "Lỗi thêm!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddChiTieuActivity.this, "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                        Log.d("Error", "Lỗi \n" + error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("Ten", edtTen.getText().toString().trim());
                params.put("SoTien", edtSoTien.getText().toString().trim());
                params.put("Ngay", edtNgay.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        edtTen = (EditText) findViewById(R.id.editTextTen);
        edtSoTien = (EditText) findViewById(R.id.editTextSoTien);
        edtNgay = (EditText) findViewById(R.id.editTextNgay);

        btnHuy = (Button) findViewById(R.id.btnHuy);
        btnThem = (Button) findViewById(R.id.btnThem);
    }
}
