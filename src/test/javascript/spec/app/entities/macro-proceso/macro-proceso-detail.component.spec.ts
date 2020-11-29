import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { MacroProcesoDetailComponent } from 'app/entities/macro-proceso/macro-proceso-detail.component';
import { MacroProceso } from 'app/shared/model/macro-proceso.model';

describe('Component Tests', () => {
  describe('MacroProceso Management Detail Component', () => {
    let comp: MacroProcesoDetailComponent;
    let fixture: ComponentFixture<MacroProcesoDetailComponent>;
    const route = ({ data: of({ macroProceso: new MacroProceso(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [MacroProcesoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MacroProcesoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MacroProcesoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load macroProceso on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.macroProceso).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});