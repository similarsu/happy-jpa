package net.similarsu.learn.jpa.base.po;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    protected long id;
    String street1;
    String street2;
    String city;
    String province;
    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "zip",column = @Column(name = "zip_code"))
                    ,@AttributeOverride(name = "plusFour",column = @Column(name = "plus"))
            }
    )
    ZipCode zipCode;
    String country;
}