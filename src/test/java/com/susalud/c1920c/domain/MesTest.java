package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class MesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mes.class);
        Mes mes1 = new Mes();
        mes1.setId(1L);
        Mes mes2 = new Mes();
        mes2.setId(mes1.getId());
        assertThat(mes1).isEqualTo(mes2);
        mes2.setId(2L);
        assertThat(mes1).isNotEqualTo(mes2);
        mes1.setId(null);
        assertThat(mes1).isNotEqualTo(mes2);
    }
}
