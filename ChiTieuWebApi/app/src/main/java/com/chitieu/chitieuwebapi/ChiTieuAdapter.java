package com.chitieu.chitieuwebapi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by MyPC on 06/04/2018.
 */

public class ChiTieuAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<ChiTieu> chiTieuList;

    public ChiTieuAdapter(MainActivity context, int layout, List<ChiTieu> chiTieuList) {
        this.context = context;
        this.layout = layout;
        this.chiTieuList = chiTieuList;
    }

    @Override
    public int getCount() {
        return chiTieuList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    private class ViewHolder {
        TextView txtTenChiTieu, txtSoTien, txtNgay;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.txtTenChiTieu = (TextView) view.findViewById(R.id.textviewChiTieuCustom);
            holder.txtSoTien = (TextView) view.findViewById(R.id.textviewSoTienCustom);
            holder.txtNgay = (TextView) view.findViewById(R.id.textviewNgayCustom);

            holder.imgDelete = (ImageView) view.findViewById(R.id.imageviewDelete);
            holder.imgEdit = (ImageView) view.findViewById(R.id.imageviewEdit);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final ChiTieu chiTieu = chiTieuList.get(i);
        holder.txtTenChiTieu.setText(chiTieu.getTen());
        holder.txtSoTien.setText(Long.toString(chiTieu.getSoTien()));
        try {
            holder.txtNgay.setText(new SimpleDateFormat("dd-MMM-yyyy").parse(chiTieu.getNgay()).toString());
        } catch (ParseException e) {
            holder.txtNgay.setText(chiTieu.getNgay());
        }

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateChiTieuActivity.class);
                intent.putExtra("dataChiTieu", chiTieu);
                context.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XacNhanXoa(chiTieu.getTen(), chiTieu.getID());
            }
        });
        return view;
    }

    private void XacNhanXoa(String ten, final long id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Bạn có muốn xóa thông tin chi tiêu " + ten + " không? ");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.DeleteData(id);
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogXoa.show();
    }
}
