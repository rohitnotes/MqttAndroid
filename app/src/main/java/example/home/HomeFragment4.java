package example.home;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.style.base.BaseFragment;
import com.style.framework.R;

import butterknife.OnClick;


public class HomeFragment4 extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayoutResID = R.layout.fragment_home_4;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initData() {

    }

}
