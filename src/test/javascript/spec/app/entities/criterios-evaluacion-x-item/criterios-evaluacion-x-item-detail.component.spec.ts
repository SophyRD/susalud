import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { CriteriosEvaluacionXItemDetailComponent } from 'app/entities/criterios-evaluacion-x-item/criterios-evaluacion-x-item-detail.component';
import { CriteriosEvaluacionXItem } from 'app/shared/model/criterios-evaluacion-x-item.model';

describe('Component Tests', () => {
  describe('CriteriosEvaluacionXItem Management Detail Component', () => {
    let comp: CriteriosEvaluacionXItemDetailComponent;
    let fixture: ComponentFixture<CriteriosEvaluacionXItemDetailComponent>;
    const route = ({ data: of({ criteriosEvaluacionXItem: new CriteriosEvaluacionXItem(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [CriteriosEvaluacionXItemDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CriteriosEvaluacionXItemDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CriteriosEvaluacionXItemDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load criteriosEvaluacionXItem on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.criteriosEvaluacionXItem).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
