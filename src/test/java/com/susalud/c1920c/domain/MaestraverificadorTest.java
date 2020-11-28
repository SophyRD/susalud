package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class MaestraverificadorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Maestraverificador.class);
        Maestraverificador maestraverificador1 = new Maestraverificador();
        maestraverificador1.setId(1L);
        Maestraverificador maestraverificador2 = new Maestraverificador();
        maestraverificador2.setId(maestraverificador1.getId());
        assertThat(maestraverificador1).isEqualTo(maestraverificador2);
        maestraverificador2.setId(2L);
        assertThat(maestraverificador1).isNotEqualTo(maestraverificador2);
        maestraverificador1.setId(null);
        assertThat(maestraverificador1).isNotEqualTo(maestraverificador2);
    }
}
