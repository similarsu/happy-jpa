package net.similarsu.learn.jpa.base.po;

import javax.persistence.Column;

public class ZipCode {
    String zip;
    @Column(name = "plus_four")
    String plusFour;
}