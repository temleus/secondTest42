package com.task;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;
import com.task.db.UserDbHelper;

/**
 * @author Leus Artem
 * @since 17.06.13
 */
public class UserFragment extends SherlockFragment {

    private static final String TAG = "UserFragment";

    private TextView nameView, surnameView, birthdateView, bioView, emailView;
    private ImageView defaultProfilePic;
    private  TestApplication app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (TestApplication) getActivity().getApplicationContext();
//        FragmentManager fm = getChildFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameView = (TextView) view.findViewById(R.id.name);
        surnameView = (TextView) view.findViewById(R.id.surname);
        birthdateView = (TextView) view.findViewById(R.id.birthdate);
        bioView = (TextView) view.findViewById(R.id.bio);
        emailView = (TextView) view.findViewById(R.id.email);

        defaultProfilePic = (ImageView) view.findViewById(R.id.default_user_pic);
        updateUserUI();
    }

    private void updateUserUI(){
        UserDbHelper.UserEntity iAm = app.getMyself();
        if(iAm == null) return; // test case
        if(iAm.name != null) nameView.setText(iAm.name);
        if(iAm.surname != null) surnameView.setText(iAm.surname);
        if(iAm.birthdate != null) birthdateView.setText(Utils.convertDateToString(iAm.birthdate));
        if(iAm.bio != null) bioView.setText(iAm.bio);
        if(iAm.email != null) emailView.setText(iAm.email);
    }
}
