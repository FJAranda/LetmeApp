package com.example.letmeapp.model;

import java.util.Comparator;

public class RequestComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return ((Request)o1).getApplicant().compareTo(((Request)o2).getApplicant());
    }
}
