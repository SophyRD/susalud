import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { UsuarioXEvaluacioXEntidadComponent } from 'app/entities/usuario-x-evaluacio-x-entidad/usuario-x-evaluacio-x-entidad.component';
import { UsuarioXEvaluacioXEntidadService } from 'app/entities/usuario-x-evaluacio-x-entidad/usuario-x-evaluacio-x-entidad.service';
import { UsuarioXEvaluacioXEntidad } from 'app/shared/model/usuario-x-evaluacio-x-entidad.model';

describe('Component Tests', () => {
  describe('UsuarioXEvaluacioXEntidad Management Component', () => {
    let comp: UsuarioXEvaluacioXEntidadComponent;
    let fixture: ComponentFixture<UsuarioXEvaluacioXEntidadComponent>;
    let service: UsuarioXEvaluacioXEntidadService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [UsuarioXEvaluacioXEntidadComponent],
      })
        .overrideTemplate(UsuarioXEvaluacioXEntidadComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UsuarioXEvaluacioXEntidadComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UsuarioXEvaluacioXEntidadService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UsuarioXEvaluacioXEntidad(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.usuarioXEvaluacioXEntidads && comp.usuarioXEvaluacioXEntidads[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
