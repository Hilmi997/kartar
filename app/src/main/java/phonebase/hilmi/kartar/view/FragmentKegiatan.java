package phonebase.hilmi.kartar.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phonebase.hilmi.kartar.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentKegiatan extends Fragment {


    public FragmentKegiatan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kegiatan, container, false);

        return v;
    }

}
