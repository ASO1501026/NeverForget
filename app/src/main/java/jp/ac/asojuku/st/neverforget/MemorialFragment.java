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
 * Use the {@link MemorialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemorialFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MemorialFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemorialFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemorialFragment newInstance(String param1, String param2) {
        MemorialFragment fragment = new MemorialFragment();
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
        return inflater.inflate(R.layout.fragment_memorial, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = this.getActivity().getSharedPreferences("memorial", Context.MODE_PRIVATE);
        String wedding = prefs.getString("wedding",null);
        String birthday = prefs.getString("birthday",null);
        String birthday1 = prefs.getString("birthday1",null);
        String birthday2 = prefs.getString("birthday2",null);

        EditText edText1 = (EditText) getView().findViewById(R.id.editText1);
        if(wedding != null){
            edText1.setText(wedding);
        }

        EditText edText2 = (EditText) getView().findViewById(R.id.editText2);
        if(birthday != null){
            edText2.setText(birthday);
        }

        EditText edText3 = (EditText) getView().findViewById(R.id.editText3);
        if(birthday1 != null){
            edText3.setText(birthday1);
        }

        EditText edText4 = (EditText) getView().findViewById(R.id.editText4);
        if(birthday2 != null){
            edText4.setText(birthday2);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        EditText edText1 = (EditText) getView().findViewById(R.id.editText1);
        EditText edText2 = (EditText) getView().findViewById(R.id.editText2);
        EditText edText3 = (EditText) getView().findViewById(R.id.editText3);
        EditText edText4 = (EditText) getView().findViewById(R.id.editText4);

        String wedding;
        //ここで例外キャッチして抜ける
        try{
            wedding = edText1.getText().toString();
        }catch (Exception e){
            wedding = null;
        }

        String birthday;

        try{
            birthday = edText2.getText().toString();
        }catch (Exception e){
            birthday = null;
        }

        String birthday1;

        try{
            birthday1 = edText3.getText().toString();
        }catch(Exception e){
            birthday1 = null;
        }

        String birthday2;

        try{
            birthday2 = edText4.getText().toString();
        }catch (Exception e){
            birthday2 = null;
        }

        //SharedPrefereceに保存
        SharedPreferences prefs = this.getActivity().getSharedPreferences("memorial",Context.MODE_PRIVATE);

        //編集状態にする
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("wedding",wedding);
        editor.putString("birthday",birthday);
        editor.putString("birthday1",birthday1);
        editor.putString("birthday2",birthday2);

        //編集状態を終えて確定させる。
        //editor.commit();
        editor.apply(); //commitの非同期版

    }
}
