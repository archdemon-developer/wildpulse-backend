package com.wildpulse.backend.models;

import com.wildpulse.backend.constants.WPDBConstants;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = WPDBConstants.WP_DB_TBL_NAME_USER, schema = WPDBConstants.WP_DB_SCHEMA_NAME)
public class WPUser {

    @Id
    @Column(name = WPDBConstants.WP_FIELD_NAME_ID, updatable = false, nullable = false)
    private String id;

    @Column(name = WPDBConstants.WP_FIELD_NAME_USERNAME)
    private String userName;

    @Column(name = WPDBConstants.WP_FIELD_NAME_EMAIL)
    private String email;

    @Column(name = WPDBConstants.WP_FIELD_NAME_EMAIL_VERIFIED)
    private boolean emailVerified;

    @Column(name = WPDBConstants.WP_FIELD_NAME_PHOTO_URL)
    private String photoUrl;

    @Column(name = WPDBConstants.WP_FIELD_NAME_IS_SUBSCRIBED)
    private boolean isSubscribed;
}
