package phonebase.hilmi.kartar.model;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by User on 21/11/2019.
 */

public class BaseModel {
    private static String TAG = "BaseModel";

    public BaseModel(Context context){
        if(queue == null){
            queue = Volley.newRequestQueue(context);
        }
    }

    RequestQueue queue;

    public interface onStringPOSTResponseListener{
        void onStringPOSTResponsed(String response, Exception e);
    }
    public interface onStringGETResponseListener{
        void onStringGETResponsed(String response, Exception e);
    }
    public interface onJsonPOSTResponseListener{
        void onJsonPOSTResponsed(JSONObject object, Exception e);
    }
    public interface onJsonGETResponseListener{
        void onJsonGETResponsed(JSONObject object, Exception e);
    }
    public interface onJsonPUTResponseListener{
        void onJsonPUTResponsed(JSONObject object, Exception e);
    }
    public interface onJsonDELETEResponseListener{
        void onJsonDELETEResponsed(JSONObject object, Exception e);
    }
}
