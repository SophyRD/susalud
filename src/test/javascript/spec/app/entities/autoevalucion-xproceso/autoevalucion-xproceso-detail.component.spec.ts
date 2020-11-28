import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { AutoevalucionXprocesoDetailComponent } from 'app/entities/autoevalucion-xproceso/autoevalucion-xproceso-detail.component';
import { AutoevalucionXproceso } from 'app/shared/model/autoevalucion-xproceso.model';

describe('Component Tests', () => {
  describe('AutoevalucionXproceso Management Detail Component', () => {
    let comp: AutoevalucionXprocesoDetailComponent;
    let fixture: ComponentFixture<AutoevalucionXprocesoDetailComponent>;
    const route = ({ data: of({ autoevalucionXproceso: new AutoevalucionXproceso(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [AutoevalucionXprocesoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AutoevalucionXprocesoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AutoevalucionXprocesoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load autoevalucionXproceso on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.autoevalucionXproceso).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
