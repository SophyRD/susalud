import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { SubProcesoDetailComponent } from 'app/entities/sub-proceso/sub-proceso-detail.component';
import { SubProceso } from 'app/shared/model/sub-proceso.model';

describe('Component Tests', () => {
  describe('SubProceso Management Detail Component', () => {
    let comp: SubProcesoDetailComponent;
    let fixture: ComponentFixture<SubProcesoDetailComponent>;
    const route = ({ data: of({ subProceso: new SubProceso(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [SubProcesoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SubProcesoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SubProcesoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load subProceso on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.subProceso).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
