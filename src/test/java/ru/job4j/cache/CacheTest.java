package ru.job4j.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CacheTest {
    @Test
    public void whenAdd() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1, "base1");
        cache.add(base1);
        Base cachedBase = cache.get(base1.getId());
        assertThat(cachedBase.getName()).isEqualTo(base1.getName());
        assertThat(cachedBase.getVersion()).isEqualTo(base1.getVersion());
    }

    @Test
    public void whenAddThenDelete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1, "base1");
        cache.add(base1);
        cache.delete(base1);
        assertThat(cache.size()).isEqualTo(0);
    }

    @Test
    public void whenAddThenUpdate() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1, "base1");
        Base base2 = new Base(1, 1, "base2");
        cache.add(base1);
        cache.update(base2);
        Base cachedBase = cache.get(base1.getId());
        assertThat(cachedBase.getName()).isEqualTo(base2.getName());
        assertThat(cachedBase.getVersion()).isEqualTo(base1.getVersion() + 1);
    }

}