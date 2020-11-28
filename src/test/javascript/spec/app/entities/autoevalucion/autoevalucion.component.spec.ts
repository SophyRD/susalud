import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { AutoevalucionComponent } from 'app/entities/autoevalucion/autoevalucion.component';
import { AutoevalucionService } from 'app/entities/autoevalucion/autoevalucion.service';
import { Autoevalucion } from 'app/shared/model/autoevalucion.model';

describe('Component Tests', () => {
  describe('Autoevalucion Management Component', () => {
    let comp: AutoevalucionComponent;
    let fixture: ComponentFixture<AutoevalucionComponent>;
    let service: AutoevalucionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [AutoevalucionComponent],
      })
        .overrideTemplate(AutoevalucionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AutoevalucionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AutoevalucionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Autoevalucion(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.autoevalucions && comp.autoevalucions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
