package com.zeronight.templet.module.window;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2018/1/31.
 */

public class WindowService extends Service {

    private TextView tv_close;
    private int i = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.i("service --- create");
        createFloatView();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void createFloatView() {
//        View view = LayoutInflater.from(BaseApplication.getInstance().getApplicationContext())
//                .inflate(R.layout.view_window, null, false);
//        tv_close = view.findViewById(R.id.tv_close);
//        Logger.i("service --- create2");
//        tv_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FloatWindow.get().hide();
//            }
//        });
//        Logger.i("service --- create3");
//        FloatWindow
//                .with(getApplicationContext())
//                .setView(view)
//                .setWidth(Screen.width,0.2f)
//                .setHeight(Screen.width,0.2f)
//                .setX(Screen.width,0.7f)
//                .setY(Screen.height,0.2f)
//                .setMoveType(MoveType.back)
//                .setMoveStyle(300,null)
//                .setDesktopShow(true)
//                .build();

    }




}
