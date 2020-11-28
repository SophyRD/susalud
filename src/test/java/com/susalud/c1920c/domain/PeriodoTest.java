package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class PeriodoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Periodo.class);
        Periodo periodo1 = new Periodo();
        periodo1.setId(1L);
        Periodo periodo2 = new Periodo();
        periodo2.setId(periodo1.getId());
        assertThat(periodo1).isEqualTo(periodo2);
        periodo2.setId(2L);
        assertThat(periodo1).isNotEqualTo(periodo2);
        periodo1.setId(null);
        assertThat(periodo1).isNotEqualTo(periodo2);
    }
}
