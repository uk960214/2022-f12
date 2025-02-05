package com.woowacourse.f12.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "keyboard", uniqueConstraints = {
        @UniqueConstraint(name = "NAME_UNIQUE", columnNames = {"name"})})
@Getter
public class Keyboard {

    private static final int MAXIMUM_IMAGE_URL_LENGTH = 65535;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_url", nullable = false, length = MAXIMUM_IMAGE_URL_LENGTH)
    private String imageUrl;

    @Formula("(SELECT COUNT(1) FROM review r WHERE r.product_id = id)")
    private int reviewCount;

    @Formula("(SELECT IFNULL(AVG(r.rating), 0) FROM review r WHERE r.product_id = id)")
    private double rating;

    protected Keyboard() {
    }

    @Builder
    private Keyboard(final Long id, final String name, final String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Keyboard keyboard = (Keyboard) o;
        return Objects.equals(id, keyboard.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
