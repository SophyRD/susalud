package com.uniminuto.susalud.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.uniminuto.susalud.web.rest.TestUtil;

public class SubProcesoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubProceso.class);
        SubProceso subProceso1 = new SubProceso();
        subProceso1.setId(1L);
        SubProceso subProceso2 = new SubProceso();
        subProceso2.setId(subProceso1.getId());
        assertThat(subProceso1).isEqualTo(subProceso2);
        subProceso2.setId(2L);
        assertThat(subProceso1).isNotEqualTo(subProceso2);
        subProceso1.setId(null);
        assertThat(subProceso1).isNotEqualTo(subProceso2);
    }
}
