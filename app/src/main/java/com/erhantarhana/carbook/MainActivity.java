package com.erhantarhana.carbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.erhantarhana.carbook.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ArrayList<Car> carArrayList;
    CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        carArrayList = new ArrayList<>();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        carAdapter = new CarAdapter(carArrayList);
        binding.recyclerView.setAdapter(carAdapter);

        getData();
    }

    private void getData() {
        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Cars", MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM cars", null);
            int brandIx = cursor.getColumnIndex("carbrand");
            int idIx = cursor.getColumnIndex("id");

            while(cursor.moveToNext()) {
                String brand = cursor.getString(brandIx);
                int id = cursor.getInt(idIx);
                Car car = new Car(brand, id);
                carArrayList.add(car);
            }

            carAdapter.notifyDataSetChanged();

            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.car_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_car) {
            Intent intent = new Intent(this, CarActivity.class);
            intent.putExtra("info", "new");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}