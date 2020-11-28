package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class UsuariosXevaluacionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsuariosXevaluacion.class);
        UsuariosXevaluacion usuariosXevaluacion1 = new UsuariosXevaluacion();
        usuariosXevaluacion1.setId(1L);
        UsuariosXevaluacion usuariosXevaluacion2 = new UsuariosXevaluacion();
        usuariosXevaluacion2.setId(usuariosXevaluacion1.getId());
        assertThat(usuariosXevaluacion1).isEqualTo(usuariosXevaluacion2);
        usuariosXevaluacion2.setId(2L);
        assertThat(usuariosXevaluacion1).isNotEqualTo(usuariosXevaluacion2);
        usuariosXevaluacion1.setId(null);
        assertThat(usuariosXevaluacion1).isNotEqualTo(usuariosXevaluacion2);
    }
}
