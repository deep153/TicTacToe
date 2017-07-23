package k00380391.deep.tictactoe;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Deep Gandhi on 05-03-2016.
 */
public class GridAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    Boolean circle = true;
    ArrayList<Integer> circle_positions = new ArrayList<Integer>();
    ArrayList<Integer> cross_positions = new ArrayList<Integer>();
    List<int[]> winning_positions = new ArrayList<int[]>();
    TextView turn_text;
    Button reset_btn;
    int win_position = 0;

    int count = 0;
    Boolean clickable = true;

    public GridAdapter(Context mContext,TextView t,Button b){
        context = mContext;
        turn_text = t;
        reset_btn = b;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int[] p1 = {0,1,2};
        int[] p2 = {3,4,5};
        int[] p3 = {6,7,8};
        int[] p4 = {0,3,6};
        int[] p5 = {1,4,7};
        int[] p6 = {2,5,8};
        int[] p7 = {0,4,8};
        int[] p8 = {2,4,6};

        winning_positions.add(p1);
        winning_positions.add(p2);
        winning_positions.add(p3);
        winning_positions.add(p4);
        winning_positions.add(p5);
        winning_positions.add(p6);
        winning_positions.add(p7);
        winning_positions.add(p8);

    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rootView;

        rootView = inflater.inflate(R.layout.grid_item,null);
        final Button ib = (Button) rootView.findViewById(R.id.img_btn);
        //tv.setText(numbers[position]);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Drawable img = context.getResources().getDrawable( R.drawable.circle );
                //img.setBounds(60,60,0,0);
                if(clickable) {
                    if (circle) {
                        ib.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circle, 0, 0, 0);
                        circle = false;
                        circle_positions.add(position);
                        turn_text.setText("X TURN");
                    } else {
                        ib.setCompoundDrawablesWithIntrinsicBounds(R.drawable.close, 0, 0, 0);
                        circle = true;
                        cross_positions.add(position);
                        turn_text.setText("O TURN");
                    }
                    count++;
                    if(count>=5){
                        displayWinner();
//                        for(int element : winning_positions.get(win_position)){
//
//                        }

                    }

                    if(count == 9){
                        Toast.makeText(context,"Game Draw",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        return rootView;
    }

    public ArrayList<Integer> getCirclePositions() {
        return circle_positions;
    }

    public ArrayList<Integer> getCrossPositions() {
        return cross_positions;
    }

    public void displayWinner() {

        Boolean circle_win = false;
        Boolean cross_win = false;

        for (int i = 0; i < winning_positions.size(); i++) {
            //Log.e("positions", Arrays.toString(winning_positions.get(i)));
            int match_circle_count = 0;
            int match_cross_count = 0;
            for(int element : winning_positions.get(i)){
                //Log.e("positions", element +"");
                for(int circle : circle_positions){
                    //Log.e("positions", circle +"");
                    if(element == circle){
                        match_circle_count++;
                        break;
                    }
                }

                for(int cross : cross_positions){
                    //Log.e("positions", circle +"");
                    if(element == cross){
                        match_cross_count++;
                        break;
                    }
                }
            }

            win_position = i;

            if(match_circle_count == 3){
                circle_win = true;
                break;
            }else if(match_cross_count == 3){
                cross_win = true;
                break;
            }

        }

        if(circle_win){
            Toast.makeText(context,"Circle wins",Toast.LENGTH_SHORT).show();
            turn_text.setText("Circle Wins");
            clickable = false;
            //Log.e("positions", Arrays.toString(winning_positions.get(win_position)));
        }else if (cross_win) {
            turn_text.setText("Cross Wins");
            Toast.makeText(context,"Cross wins",Toast.LENGTH_SHORT).show();
            clickable = false;
            //Log.e("positions", Arrays.toString(winning_positions.get(win_position)));
        }
    }

    public void resetGrid() {

        clickable = true;
        circle_positions.clear();
        cross_positions.clear();
        count = 0;
        turn_text.setText("O Turn");
    }

}
