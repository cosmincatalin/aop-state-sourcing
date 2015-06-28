package com.models;

import com.mongodb.DBObject;

public interface Model {
    public DBObject toBSON();
}
