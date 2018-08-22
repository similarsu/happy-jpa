package net.similarsu.learn.jpa.base.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ZipCode {
    @Column(name = "zip_code")
    String zip;
    String plusFour;
}