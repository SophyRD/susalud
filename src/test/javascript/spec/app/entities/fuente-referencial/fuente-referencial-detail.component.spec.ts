import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { FuenteReferencialDetailComponent } from 'app/entities/fuente-referencial/fuente-referencial-detail.component';
import { FuenteReferencial } from 'app/shared/model/fuente-referencial.model';

describe('Component Tests', () => {
  describe('FuenteReferencial Management Detail Component', () => {
    let comp: FuenteReferencialDetailComponent;
    let fixture: ComponentFixture<FuenteReferencialDetailComponent>;
    const route = ({ data: of({ fuenteReferencial: new FuenteReferencial(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [FuenteReferencialDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FuenteReferencialDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FuenteReferencialDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load fuenteReferencial on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fuenteReferencial).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
