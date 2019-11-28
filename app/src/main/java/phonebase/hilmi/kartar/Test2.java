package phonebase.hilmi.kartar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Test2 extends AppCompatActivity {

    EditText name;
    ImageView imageView;
    ImageView choseeimage;
    Button  btnUpload;




    private Bitmap bitmap;
    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";


    static final int PICK_IMAGE_REQUEST = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String UPLOAD_URL ="http://10.10.0.10/kartar/api/upload.php";
    private String TAG = "Upload";



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);




        name= (EditText)findViewById(R.id.name);
        choseeimage= (ImageView) findViewById(R.id.choseeCamera);
        btnUpload = (Button)findViewById(R.id.upload);
        imageView = (ImageView)findViewById(R.id.previewImage);





        choseeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                opsiChoseImage();
                showFileChooser();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().length() <= 0)
                {
                    name.setError("Please Enter Name !");
                }
                else if (bitmap==null)
                {
                    Toast.makeText(Test2.this,"Please Upload Image",Toast.LENGTH_SHORT).show();
                }
                else {
                    uploadImage();

                }
            }
        });



    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }






//choose Image
//    public void showFileChooser(){
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            chooseImage.setEnabled(false);
//            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
//        }
//        Intent camera_intent = new  Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(camera_intent, PICK_IMAGE_REQUEST);
//    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        if (requestCode == PICK_IMAGE_REQUEST){
//            Bitmap photo = (Bitmap) data.getExtras().get("image/*");
//        }
//    }









    private void showFileChooser() {
//Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,PICK_IMAGE_REQUEST);



//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK ) {
//            Bitmap photo = (Bitmap) data.getExtras().get("image");
//            imageView.setImageBitmap(photo);
//        }

        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
            switch (requestCode){
                case PICK_IMAGE_REQUEST:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    Bitmap photo = (Bitmap) data.getExtras().get("image");
                    imageView.setImageBitmap(photo);
//                    imageView.setImageURI(selectedImage);
                    break;
            }


        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Test2.this.RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                imageView.setVisibility(View.VISIBLE);
                bitmap = MediaStore.Images.Media.getBitmap(Test2.this.getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(Test2.this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Log.d(TAG, "onResponse: " + s + "status: ok");
                        Toast.makeText(Test2.this,s , Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();
                        Log.d(TAG, "onErrorResponse: " + volleyError);
                        //Showing toast
                        Toast.makeText(Test2.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                String name1 = name.getText().toString().trim();

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name1);

                Log.d(TAG, "getParams: ");

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(Test2.this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }


}


