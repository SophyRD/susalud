import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { MacroProcesoComponent } from 'app/entities/macro-proceso/macro-proceso.component';
import { MacroProcesoService } from 'app/entities/macro-proceso/macro-proceso.service';
import { MacroProceso } from 'app/shared/model/macro-proceso.model';

describe('Component Tests', () => {
  describe('MacroProceso Management Component', () => {
    let comp: MacroProcesoComponent;
    let fixture: ComponentFixture<MacroProcesoComponent>;
    let service: MacroProcesoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [MacroProcesoComponent],
      })
        .overrideTemplate(MacroProcesoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MacroProcesoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MacroProcesoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MacroProceso(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.macroProcesos && comp.macroProcesos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
