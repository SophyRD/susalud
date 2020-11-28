import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { AutoevalucionDetailComponent } from 'app/entities/autoevalucion/autoevalucion-detail.component';
import { Autoevalucion } from 'app/shared/model/autoevalucion.model';

describe('Component Tests', () => {
  describe('Autoevalucion Management Detail Component', () => {
    let comp: AutoevalucionDetailComponent;
    let fixture: ComponentFixture<AutoevalucionDetailComponent>;
    const route = ({ data: of({ autoevalucion: new Autoevalucion(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [AutoevalucionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AutoevalucionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AutoevalucionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load autoevalucion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.autoevalucion).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
