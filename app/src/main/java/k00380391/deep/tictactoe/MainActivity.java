package k00380391.deep.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    Button reset_btn;
    TextView turn_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.grid);

        reset_btn = (Button) findViewById(R.id.reset_btn);
        turn_id = (TextView) findViewById(R.id.turn_id);

        final GridAdapter adapter = new GridAdapter(this,turn_id,reset_btn);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,numbers);
        gridView.setAdapter(adapter);

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView.setAdapter(null);
                gridView.setAdapter(adapter);
                adapter.resetGrid();
            }
        });

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

//        circle_positions = adapter.getCirclePositions();
//        cross_positions = adapter.getCrossPositions();
//
//        Toast.makeText(this,circle_positions.toString(),Toast.LENGTH_SHORT).show();
    }
}
