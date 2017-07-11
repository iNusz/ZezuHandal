package com.seunghoshin.android.zezuhan_1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteActivity extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference zezuRef;

    @BindView(R.id.editHome)
    EditText editHome;
    @BindView(R.id.editAdress)
    EditText editAdress;
    @BindView(R.id.editPrice)
    EditText editPrice;
    @BindView(R.id.btnPost)
    Button btnPost;
    @BindView(R.id.btnFile)
    Button btnFile;
    @BindView(R.id.txtImage)
    TextView txtImage;

    // 스토리지 영역 추가
    private StorageReference mStorageRef;

    // 프로그래스 다이얼로그
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 데이터 베이스 래퍼런스
        database = FirebaseDatabase.getInstance();
        zezuRef = database.getReference("zezu");

        // 스토리지 래퍼런스
        mStorageRef = FirebaseStorage.getInstance().getReference();
        setContentView(R.layout.activity_write);


        //다이얼로그
        dialog = new ProgressDialog(this);
        dialog.setTitle("사진 업로드");
        dialog.setMessage("사진을 업로드 중입니다... 잠시만 기다려주세요");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        ButterKnife.bind(this);

    }

    //데이터 전송
    public void postData(View view){
        dialog.show();

        String imagePath = txtImage.getText().toString();
        // 이미지가 있으면 이미지 경로를 받아서 저장해야 되기 때문에
        // 이미지를 먼저 업로드 한다
        if (imagePath != null && !"".equals(imagePath)) {
            uploadFile(imagePath);
        }else{
            afterUploadFile(null);
        }
    }

    public void uploadFile(String filePath) {
        // 스마트 폰에 있는 파일의 경로
        File file = new File(filePath);
        Uri uri = Uri.fromFile(file);
        // 파이어 베이스에 있는 파일 경로
        String filename = file.getName(); // + 시간값 or UUID 추가해서 만듬
        // 데이터베이스의 키 = 값과 동일한 구조
        StorageReference fileRef = mStorageRef.child(filename);

        fileRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // 파이어베이스 스토리지에 방금 업로드한 파일의 경로
                        @SuppressWarnings("VisibleForTests")
                        Uri uploadedUri = taskSnapshot.getDownloadUrl();
                        afterUploadFile(uploadedUri);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Log.e("FBStorage", "Upload Fail:" + exception.getMessage());
                    }
                });
    }


    public void afterUploadFile(Uri imageUri){

        String dtHomeName = editHome.getText().toString();
        String dtAdress = editAdress.getText().toString();
        String dtPrice = editPrice.getText().toString();

        // 파이어 베이스 데이터베이스에 데이터 넣기
        // 1. 데이터 객체 생성
        ZezuInfo info = new ZezuInfo(dtHomeName,dtAdress,dtPrice);

        if(imageUri != null){
            info.fileUriString = imageUri.toString();
        }

        // 2. 입력할 데이터의 키 생성
        String zezuKey = zezuRef.push().getKey(); // 자동생성된 키를 가져온다
        // 3. key아래쪽으로 한칸 내려간 노드에서 작업을 할 것이다 / 생성된 키를 래퍼런스로 데이터를 입력
        zezuRef.child(zezuKey).setValue(info);
        // 데이터 입력후 창 닫기
        dialog.dismiss();
        finish();


    }
    // 화면에 Photo 버튼에서 자동 링크
    public void gotoGallery(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // 가. 이미지 선택창 호출
        startActivityForResult(Intent.createChooser(intent, "앱을 선택하세요"), 100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 나. 이미지 선택창에서 선택된 이미지의 경로 추출
                case 100:
                    Uri imageUri = data.getData();
                    //  파일 이름을 가져온다
//                    File file = new File(imageUri.getPath());
//                    txtImage.setText(file.getName());
                    String filePath = getPathFromUri(this, imageUri);
                    txtImage.setText(filePath);
                    break;
            }
        }
    }

    // Uri 에서 실제 경로 꺼내는 함수
    public static String getPathFromUri(Context context, Uri uri) {
        String realPath = "";

        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor.moveToNext()) {
            realPath = cursor.getString(cursor.getColumnIndex("_data"));
        }
        cursor.close();
        return realPath;
    }
}
