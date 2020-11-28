import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { AutoevalucionXprocesoComponent } from 'app/entities/autoevalucion-xproceso/autoevalucion-xproceso.component';
import { AutoevalucionXprocesoService } from 'app/entities/autoevalucion-xproceso/autoevalucion-xproceso.service';
import { AutoevalucionXproceso } from 'app/shared/model/autoevalucion-xproceso.model';

describe('Component Tests', () => {
  describe('AutoevalucionXproceso Management Component', () => {
    let comp: AutoevalucionXprocesoComponent;
    let fixture: ComponentFixture<AutoevalucionXprocesoComponent>;
    let service: AutoevalucionXprocesoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [AutoevalucionXprocesoComponent],
      })
        .overrideTemplate(AutoevalucionXprocesoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AutoevalucionXprocesoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AutoevalucionXprocesoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AutoevalucionXproceso(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.autoevalucionXprocesos && comp.autoevalucionXprocesos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
