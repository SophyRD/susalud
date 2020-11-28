import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { CriteriosEvaluacionDetailComponent } from 'app/entities/criterios-evaluacion/criterios-evaluacion-detail.component';
import { CriteriosEvaluacion } from 'app/shared/model/criterios-evaluacion.model';

describe('Component Tests', () => {
  describe('CriteriosEvaluacion Management Detail Component', () => {
    let comp: CriteriosEvaluacionDetailComponent;
    let fixture: ComponentFixture<CriteriosEvaluacionDetailComponent>;
    const route = ({ data: of({ criteriosEvaluacion: new CriteriosEvaluacion(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [CriteriosEvaluacionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CriteriosEvaluacionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CriteriosEvaluacionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load criteriosEvaluacion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.criteriosEvaluacion).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
