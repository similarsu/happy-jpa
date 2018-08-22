package net.similarsu.learn.jpa.base.po;

import javax.persistence.Embeddable;

@Embeddable
public class ZipCode {
    String zip;
    String plusFour;
}