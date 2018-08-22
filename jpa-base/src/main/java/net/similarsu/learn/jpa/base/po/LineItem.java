package net.similarsu.learn.jpa.base.po;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class LineItem {
    @EmbeddedId
    private LineItemKey lineItemKey;
}
