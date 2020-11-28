package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class UsuarioXEvaluacioXEntidadTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsuarioXEvaluacioXEntidad.class);
        UsuarioXEvaluacioXEntidad usuarioXEvaluacioXEntidad1 = new UsuarioXEvaluacioXEntidad();
        usuarioXEvaluacioXEntidad1.setId(1L);
        UsuarioXEvaluacioXEntidad usuarioXEvaluacioXEntidad2 = new UsuarioXEvaluacioXEntidad();
        usuarioXEvaluacioXEntidad2.setId(usuarioXEvaluacioXEntidad1.getId());
        assertThat(usuarioXEvaluacioXEntidad1).isEqualTo(usuarioXEvaluacioXEntidad2);
        usuarioXEvaluacioXEntidad2.setId(2L);
        assertThat(usuarioXEvaluacioXEntidad1).isNotEqualTo(usuarioXEvaluacioXEntidad2);
        usuarioXEvaluacioXEntidad1.setId(null);
        assertThat(usuarioXEvaluacioXEntidad1).isNotEqualTo(usuarioXEvaluacioXEntidad2);
    }
}
