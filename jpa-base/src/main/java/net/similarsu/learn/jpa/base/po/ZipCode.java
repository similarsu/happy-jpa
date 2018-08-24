package net.similarsu.learn.jpa.base.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class ZipCode {
    String zip;
    @Column(name = "plus_four")
    String plusFour;
}