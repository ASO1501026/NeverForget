package jp.ac.asojuku.st.neverforget;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PropertyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PropertyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public PropertyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PropertyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PropertyFragment newInstance(String param1, String param2) {
        PropertyFragment fragment = new PropertyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_property, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = this.getActivity().getSharedPreferences("property", Context.MODE_PRIVATE);
        String car_number = prefs.getString("car_number",null);
        int phone_number = prefs.getInt("phone_number",0);

        EditText edText1 = (EditText) getView().findViewById(R.id.editText1);
        if(car_number != null){
            edText1.setText(car_number);
        }

        EditText edText2 = (EditText) getView().findViewById(R.id.editText2);
        if(phone_number != 0){
            edText2.setText(Integer.toString(phone_number));
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        EditText edText1 = (EditText) getView().findViewById(R.id.editText1);
        EditText edText2 = (EditText) getView().findViewById(R.id.editText2);

        String car_number;
        //ここで例外キャッチして抜ける
        try{
            car_number = edText1.getText().toString();
        }catch (Exception e){
            car_number = null;
        }

        int phone_number;

        try{
            phone_number = Integer.parseInt(edText2.getText().toString());
        }catch (NumberFormatException e){
            phone_number = 0;
        }

        //SharedPrefereceに保存
        SharedPreferences prefs = this.getActivity().getSharedPreferences("property",Context.MODE_PRIVATE);

        //編集状態にする
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("car_number",car_number);
        editor.putInt("phone_number",phone_number);

        //編集状態を終えて確定させる。
        //editor.commit();
        editor.apply(); //commitの非同期版

    }
}
