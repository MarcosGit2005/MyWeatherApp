package com.example.myweatherbase.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.List;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdaptadorRecycleView extends RecyclerView.Adapter<AdaptadorRecycleView.ViewHolder> {
    private LayoutInflater layoutInflater;
    private java.util.List<List> lists;
    private View.OnClickListener onClickListener;
    public AdaptadorRecycleView(Context context, ArrayList<List> list){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lists = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_list,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List list = lists.get(position);

        ImageDownloader.downloadImage(Parameters.ICON_URL_PRE + list.weather.get(0).icon + Parameters.ICON_URL_POST, holder.imageView);
        holder.desc.setText(list.weather.get(0).description);

        Date date = new Date((long) list.dt*1000);
        SimpleDateFormat dateDayOfWeek = new SimpleDateFormat("EEE");
        SimpleDateFormat dateDay = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm z");
        holder.diaSemana.setText(dateDayOfWeek.format(date));
        holder.fecha.setText(dateDay.format(date));
        holder.hora.setText(hour.format(date));

        holder.temp.setText("Temp "+list.main.temp+"º");
        holder.tempMax.setText("Max "+list.main.temp_max+"º");
        holder.tempMin.setText("Min "+list.main.temp_min+"º");
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView diaSemana, fecha, hora, desc, temp, tempMax, tempMin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            desc = itemView.findViewById(R.id.textViewDesc);
            diaSemana = itemView.findViewById(R.id.textViewDayOfWeek);
            fecha = itemView.findViewById(R.id.textViewDate);
            hora = itemView.findViewById(R.id.textViewHour);
            temp = itemView.findViewById(R.id.textViewTemperature);
            tempMax = itemView.findViewById(R.id.textViewMaxTemperature);
            tempMin = itemView.findViewById(R.id.textViewMinTemperature);
        }
    }
}
