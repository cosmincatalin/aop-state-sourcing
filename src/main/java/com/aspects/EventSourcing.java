package com.aspects;

import com.models.Model;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class EventSourcing {

    private DB db;

    @Autowired
    public EventSourcing(DB db) {
        this.db = db;
    }

    @After("execution(* com.services.*.add*(*))")
    public void afterAdvice(JoinPoint joinPoint) {
        for (Object o : joinPoint.getArgs()) {
            if ( o instanceof Model) {
                DBCollection collection = db.getCollection(joinPoint.getTarget().getClass().getSimpleName().toLowerCase());
                collection.insert(((Model) o).toBSON());
            }
        }
    }
}
