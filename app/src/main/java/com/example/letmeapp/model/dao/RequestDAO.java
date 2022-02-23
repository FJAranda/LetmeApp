package com.example.letmeapp.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.letmeapp.model.Request;

import java.util.List;

@Dao
public interface RequestDAO {
    @Insert()
    long insert(Request request);

    @Update()
    void update(Request request);

    @Delete()
    void delete(Request request);

    @Query("DELETE FROM request")
    void deleteAll();

    @Query("SELECT * FROM request ORDER BY item ASC")
    List<Request> selectAll();

    @Query("SELECT * FROM request WHERE item=:item")
    Request findByItem(String item);

    @Query("SELECT * FROM request WHERE applicant=:applicant")
    List<Request> selectByApplicant(String applicant);
}
