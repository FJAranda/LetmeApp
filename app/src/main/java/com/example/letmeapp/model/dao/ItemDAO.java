package com.example.letmeapp.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.letmeapp.model.Item;

import java.util.List;

@Dao
public interface ItemDAO {
    @Insert()
    long insert(Item item);

    @Update()
    void update(Item item);

    @Delete()
    void delete(Item item);

    @Query("DELETE FROM item")
    void deleteAll();

    @Query("SELECT * FROM item ORDER BY nombre ASC")
    List<Item> selectAll();

    @Query("SELECT * FROM item WHERE nombre=:nombre")
    Item findByNombre(String nombre);

    @Query("SELECT * FROM item WHERE owner=:owner ORDER BY nombre ASC")
    List<Item> selectByOwner(String owner);
}
