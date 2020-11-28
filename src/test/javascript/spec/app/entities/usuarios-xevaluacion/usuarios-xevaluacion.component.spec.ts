import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { UsuariosXevaluacionComponent } from 'app/entities/usuarios-xevaluacion/usuarios-xevaluacion.component';
import { UsuariosXevaluacionService } from 'app/entities/usuarios-xevaluacion/usuarios-xevaluacion.service';
import { UsuariosXevaluacion } from 'app/shared/model/usuarios-xevaluacion.model';

describe('Component Tests', () => {
  describe('UsuariosXevaluacion Management Component', () => {
    let comp: UsuariosXevaluacionComponent;
    let fixture: ComponentFixture<UsuariosXevaluacionComponent>;
    let service: UsuariosXevaluacionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [UsuariosXevaluacionComponent],
      })
        .overrideTemplate(UsuariosXevaluacionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UsuariosXevaluacionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UsuariosXevaluacionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UsuariosXevaluacion(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.usuariosXevaluacions && comp.usuariosXevaluacions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
