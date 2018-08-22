package net.similarsu.learn.jpa.base.po;

import javax.persistence.*;

@Entity
@IdClass(LineItemKey.class)
public class LineItem {
    @Id
    private Integer customerOrder;
    @Id
    private int itemId;
}
