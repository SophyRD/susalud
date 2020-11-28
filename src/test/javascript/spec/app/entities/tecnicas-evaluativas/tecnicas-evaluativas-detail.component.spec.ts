import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { TecnicasEvaluativasDetailComponent } from 'app/entities/tecnicas-evaluativas/tecnicas-evaluativas-detail.component';
import { TecnicasEvaluativas } from 'app/shared/model/tecnicas-evaluativas.model';

describe('Component Tests', () => {
  describe('TecnicasEvaluativas Management Detail Component', () => {
    let comp: TecnicasEvaluativasDetailComponent;
    let fixture: ComponentFixture<TecnicasEvaluativasDetailComponent>;
    const route = ({ data: of({ tecnicasEvaluativas: new TecnicasEvaluativas(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [TecnicasEvaluativasDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TecnicasEvaluativasDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TecnicasEvaluativasDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tecnicasEvaluativas on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tecnicasEvaluativas).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
