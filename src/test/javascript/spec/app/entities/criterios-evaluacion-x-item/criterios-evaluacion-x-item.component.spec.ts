import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { CriteriosEvaluacionXItemComponent } from 'app/entities/criterios-evaluacion-x-item/criterios-evaluacion-x-item.component';
import { CriteriosEvaluacionXItemService } from 'app/entities/criterios-evaluacion-x-item/criterios-evaluacion-x-item.service';
import { CriteriosEvaluacionXItem } from 'app/shared/model/criterios-evaluacion-x-item.model';

describe('Component Tests', () => {
  describe('CriteriosEvaluacionXItem Management Component', () => {
    let comp: CriteriosEvaluacionXItemComponent;
    let fixture: ComponentFixture<CriteriosEvaluacionXItemComponent>;
    let service: CriteriosEvaluacionXItemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [CriteriosEvaluacionXItemComponent],
      })
        .overrideTemplate(CriteriosEvaluacionXItemComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CriteriosEvaluacionXItemComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CriteriosEvaluacionXItemService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CriteriosEvaluacionXItem(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.criteriosEvaluacionXItems && comp.criteriosEvaluacionXItems[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
