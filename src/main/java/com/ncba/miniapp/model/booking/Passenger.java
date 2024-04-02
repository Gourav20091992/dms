package com.ncba.miniapp.model.booking;

import com.ncba.miniapp.model.RequestResponseLog;
import jakarta.persistence.*;
import lombok.*;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passenger")
@EqualsAndHashCode(callSuper = true)
public class Passenger extends RequestResponseLog {

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_code")
    private String phoneCode;

    @Column(name = "email")
    private String email;

    @Column(name = "born_on")
    private String bornOn;

    @Column(name = "title")
    private String title;

    @Column(name = "gender")
    private String gender;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "type")
    private String type;

}