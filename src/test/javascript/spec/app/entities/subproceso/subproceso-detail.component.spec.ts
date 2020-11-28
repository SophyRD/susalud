import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { SubprocesoDetailComponent } from 'app/entities/subproceso/subproceso-detail.component';
import { Subproceso } from 'app/shared/model/subproceso.model';

describe('Component Tests', () => {
  describe('Subproceso Management Detail Component', () => {
    let comp: SubprocesoDetailComponent;
    let fixture: ComponentFixture<SubprocesoDetailComponent>;
    const route = ({ data: of({ subproceso: new Subproceso(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [SubprocesoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SubprocesoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SubprocesoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load subproceso on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.subproceso).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
