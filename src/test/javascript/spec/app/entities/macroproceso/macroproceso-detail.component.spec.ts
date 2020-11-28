import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { MacroprocesoDetailComponent } from 'app/entities/macroproceso/macroproceso-detail.component';
import { Macroproceso } from 'app/shared/model/macroproceso.model';

describe('Component Tests', () => {
  describe('Macroproceso Management Detail Component', () => {
    let comp: MacroprocesoDetailComponent;
    let fixture: ComponentFixture<MacroprocesoDetailComponent>;
    const route = ({ data: of({ macroproceso: new Macroproceso(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [MacroprocesoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MacroprocesoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MacroprocesoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load macroproceso on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.macroproceso).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
