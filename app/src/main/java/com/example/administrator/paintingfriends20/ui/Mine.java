package com.example.administrator.paintingfriends20.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.utils.Utils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by Administrator on 2017/6/7.
 */
public class Mine extends Activity {

    private static final int REQUEST_IMAGE = 1;
    private ImageView mIvPersonaldetailsImage;
    private TextView mTvPersonaldetailsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        Bundle extras = getIntent().getExtras();
        String headportrait = extras.getString("headportrait");     //发布人头像url
        String name = extras.getString("name");     //发布人名字
        System.out.println(headportrait + name);

        findId();
        Picasso.with(getApplicationContext()).load(Utils.URL + "upload/" + headportrait).into(mIvPersonaldetailsImage);
        mTvPersonaldetailsName.setText(name);

        mIvPersonaldetailsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MultiImageSelectorActivity.class);

                //是否调用相机拍照
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                //最大图片选择数量
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
                //设置模式(支持 单选/MultiImageSelectorActivity.EXTRA_SELECT_MODE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });
    }

    private void findId() {
        mIvPersonaldetailsImage = (ImageView) findViewById(R.id.IvPersonaldetailsImage);
        mTvPersonaldetailsName = (TextView) findViewById(R.id.TvPersonaldetailsName);
    }

    /**
     * 发布作品
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                //获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                //处理自己的逻辑
                SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                int uid = preferences.getInt("uid", 0);
                for (int i = 0; i < path.size(); i++) {
                    String imageFile = path.get(i);
                    File file = new File(imageFile);
                    if (file.exists() && file.length() > 0) {
                        AsyncHttpClient client = new AsyncHttpClient();

                        RequestParams params = new RequestParams();

                        params.put("uid", uid);
                        try {
                            params.put("img", file);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        String urls = Utils.URL + "user/edit";

                        client.post(urls, params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                                String result = new String(bytes);
                                System.out.print(result);

                                if (result.equals("1")) {

                                    Toast.makeText(getApplicationContext(), "发布成功", Toast.LENGTH_SHORT).show();
                                    Intent m = new Intent(getApplicationContext(), MainActivity.class);

                                    startActivity(m);
                                } else {

                                    Toast.makeText(getApplicationContext(), "发布失败", Toast.LENGTH_SHORT).show();
                                }
                            }


                            @Override
                            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                                Toast.makeText(getApplicationContext(), "发布失败", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            }
        }

    }
}
