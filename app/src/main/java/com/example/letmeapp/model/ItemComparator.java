package com.example.letmeapp.model;

import java.util.Comparator;

public class ItemComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return ((Item)o1).getTipo().compareTo(((Item)o2).getTipo());
    }
}
