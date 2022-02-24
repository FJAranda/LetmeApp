package com.example.letmeapp.service;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;

import androidx.room.RoomDatabase;

import com.example.letmeapp.LetMeApplication;
import com.example.letmeapp.model.Item;
import com.example.letmeapp.model.Request;
import com.example.letmeapp.model.database.MyDatabase;
import com.example.letmeapp.model.repository.ItemRepository;
import com.example.letmeapp.model.repository.ItemRepositoryRoom;
import com.example.letmeapp.model.repository.RequestRepositoryRoom;
import com.example.letmeapp.utils.MyUtils;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d("Service", "StartJob");
        //Get request with status = sended
        List<Request> requests = RequestRepositoryRoom.getInstance().getSendedRequest();
        for (Request request : requests) {
            //Get item from request
            Item item = ItemRepositoryRoom.getInstance().getItemByNombre(request.getItem());
                //If item.owner its me
                if (item.getOwner().equals(MyUtils.getUserData(LetMeApplication.getContext()).getEmail())){
                    //Defensive copying
                    Request updated = new Request(request.getId(), request.getItem(), request.getApplicant(), request.getMessage(), request.getStartDate(), request.getEndDate(), "notified");
                    //Update request from sended to notified
                    RequestRepositoryRoom.getInstance().edit(updated, request);
                    //Send broadcast
                    Intent intent = new Intent("com.example.letmeapp");
                    sendBroadcast(intent);
                    Log.d("RequestService", intent.toString());
                }
            }
        return true;
    }

    /* Method using Firebase
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d("RequestService", "StartJob");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(Request.LETREQUEST_FIRESTORE).get().addOnSuccessListener( request -> {
            request.forEach(qds ->{
                Log.d("RequestService", "Get requests");
                //Request status is sended
                if (qds.get(Request.REQUEST_STATUS) == Request.REQUEST_STATUS_SENDED){
                    //Get request item
                    DocumentReference dr = qds.getDocumentReference(Request.REQUEST_ITEM);
                    dr.get().addOnSuccessListener( item ->{
                        //Requested item owner is me
                        if (item.get(Item.ITEM_OWNER).toString().equals("/users/" + MyUtils.getUserData(LetMeApplication.getContext()).getEmail())){
                            //change request status
                            HashMap map = new HashMap<String, String>();
                            map.put(Request.REQUEST_STATUS, Request.REQUEST_STATUS_NOTIFIED);
                            db.collection(Request.LETREQUEST_FIRESTORE).document(qds.getId()).update(map)
                                    .addOnCompleteListener(v ->{
                                       //Send broadcast
                                        Intent intent = new Intent("com.example.letmeapp");
                                        sendBroadcast(intent);
                                        Log.d("RequestService", intent.toString());
                                    });
                        }
                    });
                }
            });
        });
        return true;
    }
     */

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
