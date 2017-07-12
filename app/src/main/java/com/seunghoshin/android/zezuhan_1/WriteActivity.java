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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WriteActivity extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference zezuRef;
    ZezuInfo datas = new ZezuInfo();

    @BindView(R.id.editHomeName)
    EditText editHomeName;
    @BindView(R.id.editdtAdress)
    EditText editdtAdress;
    @BindView(R.id.editmonthPrice)
    EditText editmonthPrice;
    @BindView(R.id.editdtIntro)
    EditText editdtIntro;
    @BindView(R.id.editdtPreprice)
    EditText editdtPreprice;
    @BindView(R.id.editdtDeposit)
    EditText editdtDeposit;
    @BindView(R.id.editstartDate)
    EditText editstartDate;
    @BindView(R.id.editendDate)
    EditText editendDate;
    @BindView(R.id.editarea)
    EditText editarea;
    @BindView(R.id.edithouseStyle)
    EditText edithouseStyle;
    @BindView(R.id.editnumPeople)
    EditText editnumPeople;
    @BindView(R.id.editnumRoom)
    EditText editnumRoom;
    @BindView(R.id.editnumBed)
    EditText editnumBed;
    @BindView(R.id.editnumShower)
    EditText editnumShower;
    @BindView(R.id.editnumParking)
    EditText editnumParking;

    @BindView(R.id.btnPost)
    Button btnPost;
    @BindView(R.id.btnFile)
    Button btnFile;
    @BindView(R.id.txtImage)
    TextView txtImage;
    @BindView(R.id.scrollView)
    ScrollView scrollView;


    @BindView(R.id.checkboxAirConditioner)
    CheckBox checkboxAirConditioner;
    @BindView(R.id.checkboxPublicTransport)
    CheckBox checkboxPublicTransport;
    @BindView(R.id.checkboxParking)
    CheckBox checkboxParking;
    @BindView(R.id.checkboxConvenience)
    CheckBox checkboxConvenience;
    @BindView(R.id.checkboxUpTown)
    CheckBox checkboxUpTown;
    @BindView(R.id.checkboxSchool)
    CheckBox checkboxSchool;
    @BindView(R.id.checkboxChild)
    CheckBox checkboxChild;
    @BindView(R.id.checkboxPet)
    CheckBox checkboxPet;
    @BindView(R.id.checkboxFullOption)
    CheckBox checkboxFullOption;
    @BindView(R.id.checkboxQuiet)
    CheckBox checkboxQuiet;
    @BindView(R.id.checkboxYard)
    CheckBox checkboxYard;
    @BindView(R.id.checkboxNearTheSea)
    CheckBox checkboxNearTheSea;
    @BindView(R.id.checkboxNearTheForest)
    CheckBox checkboxNearTheForest;
    @BindView(R.id.checkboxNearTheAirport)
    CheckBox checkboxNearTheAirport;

    @BindView(R.id.checkboxKitchenPublic)
    CheckBox checkboxKitchenPublic;
    @BindView(R.id.checkboxKitchen)
    CheckBox checkboxKitchen;
    @BindView(R.id.checkboxTableware)
    CheckBox checkboxTableware;
    @BindView(R.id.checkboxTv)
    CheckBox checkboxTv;
    @BindView(R.id.checkboxInternet)
    CheckBox checkboxInternet;
    @BindView(R.id.checkboxWifi)
    CheckBox checkboxWifi;
    @BindView(R.id.checkboxWasher)
    CheckBox checkboxWasher;
    @BindView(R.id.checkboxElevator)
    CheckBox checkboxElevator;
    @BindView(R.id.checkboxHeating)
    CheckBox checkboxHeating;
    @BindView(R.id.checkboxMicrowave)
    CheckBox checkboxMicrowave;
    @BindView(R.id.checkboxRefrigerator)
    CheckBox checkboxRefrigerator;

    @BindView(R.id.checkboxNonIndoorSmoking)
    CheckBox checkboxNonIndoorSmoking;
    @BindView(R.id.checkboxOkIndoorSmoking)
    CheckBox checkboxOkIndoorSmoking;
    @BindView(R.id.checkboxFireDetector)
    CheckBox checkboxFireDetector;
    @BindView(R.id.checkboxFireArm)
    CheckBox checkboxFireArm;
    @BindView(R.id.checkboxFirstAidKit)
    CheckBox checkboxFirstAidKit;
    @BindView(R.id.checkboxSafetyCard)
    CheckBox checkboxSafetyCard;

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
    public void postData(View view) {
        dialog.show();

        String imagePath = txtImage.getText().toString();
        // 이미지가 있으면 이미지 경로를 받아서 저장해야 되기 때문에
        // 이미지를 먼저 업로드 한다
        if (imagePath != null && !"".equals(imagePath)) {
            uploadFile(imagePath);
        } else {
            afterUploadFile(null);
        }


        setValueAdditional();
        setValueBasic();
        setValueFacility();
        setValueSafe();

        if (datas.getDtHomeName().equals("") || datas.getMonthPrice().equals("") || datas.getDtDeposit().equals("") ||
                datas.getDtPreprice().equals("") || datas.getArea().equals("")) {
            Toast.makeText(this, "필수 항목을 입력해 주세요", Toast.LENGTH_SHORT).show();
            scrollView.fullScroll(View.FOCUS_UP);
        } else {

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
                        dialog.dismiss();
                        Toast.makeText(WriteActivity.this, "업로드 실패!!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void afterUploadFile(Uri imageUri) {
        // todo 여기를 채워야함
        String dtHomeName = editHomeName.getText().toString();
        String dtAdress = editdtAdress.getText().toString();
        String monthPrice = editmonthPrice.getText().toString();
        String dtIntro = editdtIntro.getText().toString();
        String dtPreprice = editdtPreprice.getText().toString();
        String dtDeposit = editdtDeposit.getText().toString();
        String startDate = editstartDate.getText().toString();
        String endDate = editendDate.getText().toString();
        String area = editarea.getText().toString();
        String houseStyle = edithouseStyle.getText().toString();
        String numPeople = editnumPeople.getText().toString();
        String numRoom = editnumRoom.getText().toString();
        String numBed = editnumBed.getText().toString();
        String numShower = editnumShower.getText().toString();
        String numParking = editnumParking.getText().toString();


        // 파이어 베이스 데이터베이스에 데이터 넣기
        // 1. 데이터 객체 생성 todo 여기에도 객체를 set,get해줘야함
        ZezuInfo info = new ZezuInfo(dtHomeName, dtAdress, dtIntro, monthPrice, dtPreprice, dtDeposit, startDate, endDate, area,
                houseStyle, numPeople, numRoom, numBed, numShower, numParking);

        if (imageUri != null) {
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
    public void gotoGallery(View view) {
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

    private void setValueSafe() {
        List<String> safe = new ArrayList<>();

        // getHint 는 값이 Empty일때 hint가 나타난다
        if (checkboxNonIndoorSmoking.isChecked() == true)
            safe.add(checkboxNonIndoorSmoking.getHint().toString());
        if (checkboxOkIndoorSmoking.isChecked() == true)
            safe.add(checkboxOkIndoorSmoking.getHint().toString());
        if (checkboxFireDetector.isChecked() == true)
            safe.add(checkboxFireDetector.getHint().toString());
        if (checkboxFireArm.isChecked() == true)
            safe.add(checkboxFireArm.getHint().toString());
        if (checkboxFirstAidKit.isChecked() == true)
            safe.add(checkboxFirstAidKit.getHint().toString());
        if (checkboxSafetyCard.isChecked() == true)
            safe.add(checkboxSafetyCard.getHint().toString());

        datas.setmSafety(safe);

    }

    private void setValueFacility() {
        List<String> facility = new ArrayList<String>();
        if (checkboxKitchenPublic.isChecked() == true)
            facility.add(checkboxKitchenPublic.getHint().toString());
        if (checkboxKitchen.isChecked() == true)
            facility.add(checkboxKitchen.getHint().toString());
        if (checkboxTableware.isChecked() == true)
            facility.add(checkboxTableware.getHint().toString());
        if (checkboxTv.isChecked() == true)
            facility.add(checkboxTv.getHint().toString());
        if (checkboxAirConditioner.isChecked() == true)
            facility.add(checkboxAirConditioner.getHint().toString());
        if (checkboxInternet.isChecked() == true)
            facility.add(checkboxInternet.getHint().toString());
        if (checkboxWifi.isChecked() == true)
            facility.add(checkboxWifi.getHint().toString());
        if (checkboxWasher.isChecked() == true)
            facility.add(checkboxWasher.getHint().toString());
        if (checkboxElevator.isChecked() == true)
            facility.add(checkboxElevator.getHint().toString());
        if (checkboxHeating.isChecked() == true)
            facility.add(checkboxHeating.getHint().toString());
        if (checkboxMicrowave.isChecked() == true)
            facility.add(checkboxMicrowave.getHint().toString());
        if (checkboxRefrigerator.isChecked() == true)
            facility.add(checkboxRefrigerator.getHint().toString());

        datas.setmFacility(facility);
    }

//    private void setValueKeyword() {
//        if (checkboxPublicTransport.isChecked() == true)
//            datas.setPublicTransport(checkboxPublicTransport.getHint().toString());
//        if (checkboxParking.isChecked() == true)
//            datas.setParking(checkboxParking.getHint().toString());
//        if (checkboxConvenience.isChecked() == true)
//            datas.setConvenience(checkboxConvenience.getHint().toString());
//        if (checkboxUpTown.isChecked() == true)
//            datas.setUpTown(checkboxUpTown.getHint().toString());
//        if (checkboxSchool.isChecked() == true)
//            datas.setSchool(checkboxSchool.getHint().toString());
//        if (checkboxChild.isChecked() == true)
//            datas.setChild(checkboxChild.getHint().toString());
//        if (checkboxPet.isChecked() == true)
//            datas.setPet(checkboxPet.getHint().toString());
//        if (checkboxFullOption.isChecked() == true)
//            datas.setFullOption(checkboxFullOption.getHint().toString());
//        if (checkboxQuiet.isChecked() == true)
//            datas.setQuiet(checkboxQuiet.getHint().toString());
//        if (checkboxYard.isChecked() == true)
//            datas.setYard(checkboxYard.getHint().toString());
//        if (checkboxNearTheSea.isChecked() == true)
//            datas.setNearTheSea(checkboxNearTheSea.getHint().toString());
//        if (checkboxNearTheForest.isChecked() == true)
//            datas.setNearTheForest(checkboxNearTheForest.getHint().toString());
//        if (checkboxNearTheAirport.isChecked() == true)
//            datas.setNearTheAirport(checkboxNearTheAirport.getHint().toString());
//    }


    private void setValueAdditional() {
        datas.setHouseStyle(edithouseStyle.getText().toString());
        datas.setNumPeople(editnumPeople.getText().toString());
        datas.setNumShower(editnumShower.getText().toString());
        datas.setNumBed(editnumBed.getText().toString());
        datas.setNumRoom(editnumRoom.getText().toString());
        datas.setNumParking(editnumParking.getText().toString());
    }


    private void setValueBasic() {
        datas.setDtHomeName(editHomeName.getText().toString());
//        datas.setStartDate(editStartDate.getText().toString());
//        datas.setEndDate(editEndDate.getText().toString());
        datas.setMonthPrice(editmonthPrice.getText().toString());
        datas.setDtPreprice(editdtPreprice.getText().toString());
        datas.setDtDeposit(editdtDeposit.getText().toString());
        datas.setArea(editarea.getText().toString());
        datas.setDtAdress(editdtAdress.getText().toString());
        datas.setDtIntro(editdtIntro.getText().toString());
    }


}
