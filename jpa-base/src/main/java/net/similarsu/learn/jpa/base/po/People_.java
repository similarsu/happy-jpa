package net.similarsu.learn.jpa.base.po;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(People.class)
public class People_ {
    public static volatile SingularAttribute<People,Long> id;
    public static volatile SingularAttribute<People,String> name;
    public static volatile SingularAttribute<People,Color> color;
    public static volatile SingularAttribute<People,Float> weight;
    public static volatile SingularAttribute<People,Float> height;
    public static volatile SingularAttribute<People, LocalDate> birth;
    public static volatile SingularAttribute<People, Boolean> alive;

}
