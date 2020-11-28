import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { MaestraverificadorComponent } from 'app/entities/maestraverificador/maestraverificador.component';
import { MaestraverificadorService } from 'app/entities/maestraverificador/maestraverificador.service';
import { Maestraverificador } from 'app/shared/model/maestraverificador.model';

describe('Component Tests', () => {
  describe('Maestraverificador Management Component', () => {
    let comp: MaestraverificadorComponent;
    let fixture: ComponentFixture<MaestraverificadorComponent>;
    let service: MaestraverificadorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [MaestraverificadorComponent],
      })
        .overrideTemplate(MaestraverificadorComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MaestraverificadorComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MaestraverificadorService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Maestraverificador(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.maestraverificadors && comp.maestraverificadors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
