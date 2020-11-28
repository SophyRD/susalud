import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ProyectoTestModule } from '../../../test.module';
import { VerificadorComponent } from 'app/entities/verificador/verificador.component';
import { VerificadorService } from 'app/entities/verificador/verificador.service';
import { Verificador } from 'app/shared/model/verificador.model';

describe('Component Tests', () => {
  describe('Verificador Management Component', () => {
    let comp: VerificadorComponent;
    let fixture: ComponentFixture<VerificadorComponent>;
    let service: VerificadorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProyectoTestModule],
        declarations: [VerificadorComponent],
      })
        .overrideTemplate(VerificadorComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VerificadorComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VerificadorService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Verificador(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.verificadors && comp.verificadors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
