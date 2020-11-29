package com.uniminuto.susalud.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.uniminuto.susalud.web.rest.TestUtil;

public class MacroProcesoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MacroProceso.class);
        MacroProceso macroProceso1 = new MacroProceso();
        macroProceso1.setId(1L);
        MacroProceso macroProceso2 = new MacroProceso();
        macroProceso2.setId(macroProceso1.getId());
        assertThat(macroProceso1).isEqualTo(macroProceso2);
        macroProceso2.setId(2L);
        assertThat(macroProceso1).isNotEqualTo(macroProceso2);
        macroProceso1.setId(null);
        assertThat(macroProceso1).isNotEqualTo(macroProceso2);
    }
}
