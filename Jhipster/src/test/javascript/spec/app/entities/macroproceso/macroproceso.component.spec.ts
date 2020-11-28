import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ProyectoTestModule } from '../../../test.module';
import { MacroprocesoComponent } from 'app/entities/macroproceso/macroproceso.component';
import { MacroprocesoService } from 'app/entities/macroproceso/macroproceso.service';
import { Macroproceso } from 'app/shared/model/macroproceso.model';

describe('Component Tests', () => {
  describe('Macroproceso Management Component', () => {
    let comp: MacroprocesoComponent;
    let fixture: ComponentFixture<MacroprocesoComponent>;
    let service: MacroprocesoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProyectoTestModule],
        declarations: [MacroprocesoComponent],
      })
        .overrideTemplate(MacroprocesoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MacroprocesoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MacroprocesoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Macroproceso(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.macroprocesos && comp.macroprocesos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
