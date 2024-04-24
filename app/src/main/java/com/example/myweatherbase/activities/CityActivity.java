package com.example.myweatherbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.List;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.BaseActivity;
import com.example.myweatherbase.base.CallInterface;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CityActivity extends BaseActivity implements CallInterface, View.OnClickListener {
    private TextView textViewCity;
    private Root root;
    private double lat;
    private double lon;
    AdaptadorRecycleView adaptador;
    private RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        textViewCity = findViewById(R.id.textViewCity);
        recycler = findViewById(R.id.recyclerView);

        Bundle extras = getIntent().getExtras();
        lat = extras.getDouble("city_lat");
        lon = extras.getDouble("city_lon");

        // Mostramos la barra de progreso y ejecutamos la llamada a la API
        showProgress();
        executeCall(this);

        ItemTouchHelper objetoDeslizador = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        int positionTarget = target.getAdapterPosition();
                        int position = viewHolder.getAdapterPosition();
                        List movingList = root.list.get(position);

                        root.list.remove(movingList);
                        root.list.add(positionTarget,movingList);

                        adaptador.notifyItemMoved(position,positionTarget);
                        return true;
                    }
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        List swipedList = root.list.get(position);
                        root.list.remove(swipedList);
                        adaptador.notifyItemRemoved(position);
                        Snackbar.make(recycler,"Borrada la prevision ", Snackbar.LENGTH_SHORT)
                                .setAction("Undo", view -> {
                                    root.list.add(position,swipedList);
                                    adaptador.notifyItemInserted(position);
                                }).show();
                    }
                }
        );
        objetoDeslizador.attachToRecyclerView(recycler);
    }

    // Realizamos la llamada y recogemos los datos en un objeto Root
    @Override
    public void doInBackground() {
        root = Connector.getConector().get(Root.class,"&lat="+lat+"&lon="+lon);
    }

    // Una vez ya se ha realizado la llamada, ocultamos la barra de progreso y presentamos los datos
    @Override
    public void doInUI() {
        hideProgress();

        textViewCity.setText(root.getCity().getName());

        adaptador = new AdaptadorRecycleView(this, root.list);
        adaptador.setOnClickListener(this);
        recycler.setAdapter(adaptador);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler.getContext(),DividerItemDecoration.VERTICAL);
        recycler.addItemDecoration(dividerItemDecoration);
    }
    @Override
    public void onClick(View view) {
        List list = root.list.get(recycler.getChildAdapterPosition(view));
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("list",list);
        startActivity(intent);
    }
}