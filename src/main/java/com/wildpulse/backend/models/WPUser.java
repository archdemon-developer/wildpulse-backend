package com.wildpulse.backend.models;

import com.wildpulse.backend.constants.WPDBConstants;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = WPDBConstants.WP_DB_TBL_NAME_USER, schema = WPDBConstants.WP_DB_SCHEMA_NAME)
public class WPUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = WPDBConstants.WP_FIELD_NAME_ID, updatable = false, nullable = false)
    private Long id;

    @Column(name = WPDBConstants.WP_FIELD_NAME_USERNAME)
    private String userName;

    @Column(name = WPDBConstants.WP_FIELD_NAME_EMAIL)
    private String email;
}
