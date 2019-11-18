package demo;

import java.io.Serializable;

public class AddressId implements Serializable {
    private String SSN;
    private Integer address_no;

    public AddressId() {
    }

    public AddressId(String SSN, Integer address_no) {
        this.SSN = SSN;
        this.address_no = address_no;
    }
}
