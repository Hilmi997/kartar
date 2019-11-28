package phonebase.hilmi.kartar.model;

import java.util.List;

/**
 * Created by User on 21/11/2019.
 */

public class Anggota {
    public static final String TAG = "Anggota";

    public static class Item {
        public String Fullname;
        public String Alamat;
        public String Umur;


    }

    public interface onGetRealisasiListListener {
        void onGetListRealisasi(List<Anggota> itemList, Exception e);
    }
}
//    public static void getListRealisasi(String down_payment_id, Context mContext, final onGetRealisasiListListener listener){
//
//        String url = GlobalVars.url_IP_LOKAL+"finance/realisasi/"+down_payment_id;
//
//
//        new BaseModel(mContext).JsonRequestGET(mContext, url, null, new BaseModel.onJsonGETResponseListener() {
//            @Override
//            public void onJsonGETResponsed(JSONObject response, Exception e) {
//                //Log.d("Response", response.toString());
//                if(e!=null){
//                    listener.onGetListRealisasi(null,e);
//                    return;
//                }
//                List<Realisasi> items = null;
//                try{
//                    JSONObject obj = response;
//                    if(obj.has("status") && !obj.isNull("status")){
//                        if(obj.getInt("status")==Const.STT_ERROR) {
//                            listener.onGetListRealisasi(null, new Exception(obj.getString("msg")));
//                            return;
//                        }
//                    }
//
//                    if(obj.has("data") && !obj.isNull("data")){
//                        JSONArray array = obj.getJSONArray("data");
//                        items = new ArrayList<>();
//                        if(array.length()>0) {
//                            for (int i = 0; i < array.length(); i++) {
//                                JSONObject o = array.getJSONObject(i);
//                                items.add(getRealisasiItem(o));
//                            }
//                            listener.onGetListRealisasi(items, null);
//                        }else{
//                            listener.onGetListRealisasi(items, null);
//                        }
//                    }else{
//                        listener.onGetListRealisasi(null, new Exception("Cannot get data. Please contact your administrator."));
//                    }
//                } catch (JSONException e1) {
//                    //Log.d(TAG, e1.toString());
//                    listener.onGetListRealisasi(null, e1);
//                }
//            }
//        });

//    }
//
//}
