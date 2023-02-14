package ru.job4j.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CacheTest {

    @Test
    void whenDoesNotAdd() {
        Cache cache = new Cache();
        cache.add(new Base(1, 1));
        boolean rsl = cache.add(new Base(1, 2));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenAddAndUpdate() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        base1.setName("Andrey");
        cache.add(base1);
        Base base2 = new Base(1, 1);
        base2.setName("Denis");
        boolean rsl = cache.update(base2);
        assertThat(rsl).isTrue();
        assertThat(cache.get(1).getName()).isEqualTo("Denis");
        assertThat(cache.get(1).getVersion()).isEqualTo(2);
    }
}