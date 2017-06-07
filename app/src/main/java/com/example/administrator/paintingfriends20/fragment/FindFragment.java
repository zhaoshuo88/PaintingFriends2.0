package com.example.administrator.paintingfriends20.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.paintingfriends20.MainActivity;
import com.example.administrator.paintingfriends20.R;
import com.example.administrator.paintingfriends20.adapter.FindAdapter;
import com.example.administrator.paintingfriends20.domain.Find;
import com.example.administrator.paintingfriends20.ui.MyWorksActivity;
import com.example.administrator.paintingfriends20.ui.PutRequestActivity;
import com.example.administrator.paintingfriends20.utils.Utils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 15530 on 2017/5/21.
 */

public class FindFragment extends Fragment {

    private static final int REQUEST_IMAGE = 1 ;
    private View view;
    List<Find> findLists = new ArrayList<>();
    private ImageView mIvFindAdd;
    private RecyclerView recyclerView;
    String urls = Utils.URL + "upload/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_find,container,false);

        initView();
        recyclerView= (RecyclerView) view.findViewById(R.id.RvPictureShow);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FindAdapter adapter = new FindAdapter(findLists,getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findId();

        mIvFindAdd.setOnClickListener(new OnClick());
    }


    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.IvFindAdd:

                    //1.创建弹出式菜单对象
                    PopupMenu popup = new PopupMenu(getActivity(), v);
                    //2.获取菜单填充器
                    MenuInflater inflater = popup.getMenuInflater();
                    //3.填充菜单
                    inflater.inflate(R.menu.find_popupmenu,popup.getMenu());
                    //4.绑定菜单项的点击事件
                    popup.setOnMenuItemClickListener(new MenuItemClick());
                    //5.显示  --最重要的一部
                    popup.show();
                    break;

            }
        }
    }

    class  MenuItemClick implements PopupMenu.OnMenuItemClickListener{

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.add_find_item:
                    Intent intent = new Intent(getActivity(), MultiImageSelectorActivity.class);

                    //是否调用相机拍照
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA,true);
                    //最大图片选择数量
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT,9);
                    //设置模式(支持 单选/MultiImageSelectorActivity.EXTRA_SELECT_MODE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE,MultiImageSelectorActivity.MODE_MULTI);
                    startActivityForResult(intent,REQUEST_IMAGE);
                    break;
                case R.id.remove_find_item:
                    Intent removeIntent = new Intent();
                    removeIntent.setClass(getActivity(), MyWorksActivity.class);
                    startActivity(removeIntent);
                    break;
            }
            return false;
        }
    }

    /**
     * 发布作品
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                //获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                //处理自己的逻辑
                SharedPreferences preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                int uid = preferences.getInt("id", 0);
                for (int i = 0; i < path.size(); i++) {
                    String imageFile = path.get(i);
                    File file = new File(imageFile);
                    if (file.exists() && file.length() > 0) {
                        AsyncHttpClient client = new AsyncHttpClient();

                        RequestParams params=new RequestParams();

                        params.put("uid",uid);
                        try {
                            params.put("img",file);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        String urls = Utils.URL + "draw/add";

                        client.post(urls, params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                                String result=new String(bytes);
                                System.out.print(result);

                                if(result.equals("1")){

                                    Toast.makeText(getActivity(),"发布成功",Toast.LENGTH_SHORT).show();
                                    Intent m=new Intent(getActivity(),MainActivity.class);

                                    startActivity(m);
                                }
                                else{

                                    Toast.makeText(getActivity(),"发布失败",Toast.LENGTH_SHORT).show();
                                }
                            }


                            @Override
                            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                                Toast.makeText(getActivity(),"发布失败",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            }
        }
    }

    private void findId() {
        mIvFindAdd = (ImageView) view.findViewById(R.id.IvFindAdd);
    }

    private void initView(){

        new  Thread(){
            @Override
            public void run() {
                try {
                    String urlRequestfragmentPath = Utils.URL + "draw/?obj=9";

                    //从网络上下载图片
                    URL url = new URL(urlRequestfragmentPath);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    if (conn.getResponseCode() == 200){

                        //获得服务器响应数据
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

                        //数据
                        String retData = null;
                        String responseData = "";
                        while ((retData = in.readLine()) != null){
                            responseData += retData;
                        }

                        System.out.println(responseData);

                        JSONArray j = new JSONArray(responseData);
                        for (int i = 0 ; i < j.length();i++){
                            JSONObject item = j.getJSONObject(i);
                            int did = item.getInt("did");     //图片id
                            String durl = item.getString("durl");   //图片地址
                            int dlike=item.getInt("dlike");     //图片点赞数
                            int udid=item.getInt("udid");   //图片发布人id
                            String uimage = item.getString("uimage");   //图片发布人头像
                            String uname = item.getString("uname");

//                            System.out.println(uimage + "((((((((((((((((((((((((((((((");
//                            System.out.println(durl + "))))))))))))))))))))))");
                            String uimageUrl = urls + uimage;
                            String durlUrl = urls + durl;
                            findLists.add(new Find(did,uimageUrl,uname,durlUrl));
//                            URL urldown = new URL(urls + durl);
////                            Picasso.with(getActivity())
//                            File file = new File(getActivity().getCacheDir(), Base64.encodeToString(urldown.toString().getBytes(), Base64.DEFAULT));
//                            if (file.exists() && file.length() > 0){
//                                System.out.println("使用缓存图片~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//                                //使用缓存图片
//                                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
////                                Picasso.with(getActivity()).load(urls + durl).
//
//                                Message msg = Message.obtain();
//                                msg.obj = bitmap;
//                                handler.sendMessage(msg);
//
//                            }else {
//                                //从服务器端获取图片
//                                System.out.println("使用网络图片~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//
//                                Bitmap bitmap = Picasso.with(getActivity()).load(urls + durl).get();
////                                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//                                Message msg = Message.obtain();
//                                msg.obj = bitmap;
//                                handler.sendMessage(msg);
////                                HttpURLConnection httpURLConnection = (HttpURLConnection) urldown.openConnection();
////
////                                //1.设置请求方式
////                                httpURLConnection.setRequestMethod("GET");
////
////                                //2.设置请求时间
////                                httpURLConnection.setConnectTimeout(5000);
////
////                                //3.设置请求码
////                                int code = httpURLConnection.getResponseCode();
////                                if (code == 200){
////                                    //请求成功
////                                    InputStream inputStream = httpURLConnection.getInputStream();
////
////                                    //文件缓存
////                                    FileOutputStream fos = new FileOutputStream(file);
////                                    int len;
////                                    byte[] buffer = new byte[1024];
////                                    while((len = inputStream.read(buffer)) != -1){
////                                        fos.write(buffer,0,len);
////                                    }
////
////                                    fos.close();
////                                    inputStream.close();
////
////                                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
////                                    Message msg = Message.obtain();
////                                    msg.obj = bitmap;
////                                    handler.sendMessage(msg);
////
////                                }
//                            }

                        }
                        in.close();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
//
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Bitmap obj = (Bitmap) msg.obj;
//            //findLists.add(new Find(1,R.drawable.touxiang1,"张三",obj));
//        }
//    };
}
