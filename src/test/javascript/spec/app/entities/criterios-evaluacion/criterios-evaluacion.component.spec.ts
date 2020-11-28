import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { CriteriosEvaluacionComponent } from 'app/entities/criterios-evaluacion/criterios-evaluacion.component';
import { CriteriosEvaluacionService } from 'app/entities/criterios-evaluacion/criterios-evaluacion.service';
import { CriteriosEvaluacion } from 'app/shared/model/criterios-evaluacion.model';

describe('Component Tests', () => {
  describe('CriteriosEvaluacion Management Component', () => {
    let comp: CriteriosEvaluacionComponent;
    let fixture: ComponentFixture<CriteriosEvaluacionComponent>;
    let service: CriteriosEvaluacionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [CriteriosEvaluacionComponent],
      })
        .overrideTemplate(CriteriosEvaluacionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CriteriosEvaluacionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CriteriosEvaluacionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CriteriosEvaluacion(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.criteriosEvaluacions && comp.criteriosEvaluacions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
