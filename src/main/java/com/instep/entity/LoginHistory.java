package com.instep.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "login_history")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;
    private String ipAddress;
    private String device;
    private String browser;
    private String os;
    private String country;
    private String state;
    private String city;
    private String longitude;
    private String latitude;
    private boolean successful;
    private String failureReason;
    private LocalDateTime loginAt;
    private LocalDateTime logoutAt;

    @PrePersist
    protected void onCreate() {
        loginAt = LocalDateTime.now();
    }
}