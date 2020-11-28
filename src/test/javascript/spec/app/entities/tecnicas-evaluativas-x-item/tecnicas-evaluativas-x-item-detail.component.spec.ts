import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { TecnicasEvaluativasXItemDetailComponent } from 'app/entities/tecnicas-evaluativas-x-item/tecnicas-evaluativas-x-item-detail.component';
import { TecnicasEvaluativasXItem } from 'app/shared/model/tecnicas-evaluativas-x-item.model';

describe('Component Tests', () => {
  describe('TecnicasEvaluativasXItem Management Detail Component', () => {
    let comp: TecnicasEvaluativasXItemDetailComponent;
    let fixture: ComponentFixture<TecnicasEvaluativasXItemDetailComponent>;
    const route = ({ data: of({ tecnicasEvaluativasXItem: new TecnicasEvaluativasXItem(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [TecnicasEvaluativasXItemDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TecnicasEvaluativasXItemDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TecnicasEvaluativasXItemDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tecnicasEvaluativasXItem on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tecnicasEvaluativasXItem).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
